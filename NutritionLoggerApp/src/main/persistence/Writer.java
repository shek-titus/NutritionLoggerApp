package persistence;

import model.Day;
import model.Food;
import model.Nutrient;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Writer class to save data from current session

public class Writer {

    private ArrayList<Day> days;
    private ArrayList<Food> foods;
    private File file;
    private JsonObject jsonObject = new JsonObject();
    private FileWriter fileWriter;

    // REQUIRES: a valid list of days, list of foods, and a file to save the data to
    // MODIFIES: the data file
    // EFFECTS: Logs the days and the existing foods in the app to a data file
    public Writer(ArrayList<Day> days, ArrayList<Food> foods, File file) throws IOException {
        this.days = days;
        this.foods = foods;
        this.file = file;

        saveDays(days);
        saveExistingFoods(foods);

        fileWriter = new FileWriter(file.getPath());
        fileWriter.write(jsonObject.toJson());

    }

    // REQUIRES: a valid list of days
    // MODIFIES: file
    // EFFECTS: saves the list of days using json
    private void saveDays(ArrayList<Day> days) {
        JsonArray listOfDays = new JsonArray();
        for (Day d : days) {
            JsonObject day = new JsonObject();
            JsonArray todaysFood = new JsonArray();
            saveFoodObjects(d.getTodaysFood(), todaysFood);
            day.put("todaysFood", todaysFood);
            listOfDays.add(day);
        }
        jsonObject.put("listOfDays", listOfDays);
    }

    // REQUIRES: a list of foods, and a JsonArray to write the json food objects to
    // MODIFIES: file
    // EFFECTS: saves a list of food objects using json
    private void saveFoodObjects(ArrayList<Food> foods, JsonArray jsonFoodArray) {
        for (Food f : foods) {
            JsonObject food = new JsonObject();
            food.put("name", f.getName());
            food.put("calories", f.getNutrient(Nutrient.calories));
            food.put("totalFat", f.getNutrient(Nutrient.totalFat));
            food.put("cholesterol", f.getNutrient(Nutrient.cholesterol));
            food.put("sodium", f.getNutrient(Nutrient.sodium));
            food.put("carbs", f.getNutrient(Nutrient.carbs));
            food.put("protein", f.getNutrient(Nutrient.protein));
            food.put("iron", f.getNutrient(Nutrient.iron));
            food.put("potassium", f.getNutrient(Nutrient.potassium));
            food.put("vitamin A", f.getNutrient(Nutrient.vitA));
            food.put("vitamin C", f.getNutrient(Nutrient.vitC));
            food.put("calcium", f.getNutrient(Nutrient.calcium));
            jsonFoodArray.add(food);
        }

    }

    // REQUIRES: a valid list of foods
    // MODIFIES: file
    // EFFECTS: saves the list of existing foods using json
    private void saveExistingFoods(ArrayList<Food> foods) {
        JsonArray existingFoods = new JsonArray();
        saveFoodObjects(foods, existingFoods);
        jsonObject.put("existingFoods", existingFoods);
    }

    // EFFECTS: closes the stream of data
    public void close() throws IOException {
        fileWriter.close();
    }
}