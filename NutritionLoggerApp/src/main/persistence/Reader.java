package persistence;


import model.Day;
import model.Food;
import model.Nutrient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Reader class to load previously stored data

public class Reader {

    private File file;
    private JSONParser parser = new JSONParser();
    private FileReader reader;
    private JSONObject jsonObject;

    public Reader(File file) throws IOException, ParseException {
        this.file = file;
        this.reader = new FileReader(file.getPath());
        jsonObject = (JSONObject) parser.parse(reader);
    }

    // MODIFIES: App
    // EFFECTS: returns an array of Days from the saved data file
    public ArrayList<Day> loadDays() {
        JSONArray listOfDays = (JSONArray) jsonObject.get("listOfDays");
        ArrayList<Day> daysToLoad = new ArrayList<Day>();
        for (int n = 0; n < listOfDays.size(); n++) {
            JSONObject day = (JSONObject) listOfDays.get(n);
            Day d = new Day();
            JSONArray todaysFood = (JSONArray) day.get("todaysFood");
            d.setTodaysFood(loadFoodObjects(todaysFood));
            daysToLoad.add(d);
        }
        return daysToLoad;
    }

    // REQUIRES: A valid json array of json food objects
    // EFFECTS: takes a json array of json food objects and returns a java arraylist of food objects
    private ArrayList<Food> loadFoodObjects(JSONArray array) {
        ArrayList<Food> foodToLoad = new ArrayList<Food>();
        for (int n = 0; n < array.size(); n++) {
            JSONObject food = (JSONObject) array.get(n);
            Food f = new Food((String) food.get("name"));
            f.addNutrient(Nutrient.calories, Math.toIntExact((Long) food.get("calories")));
            f.addNutrient(Nutrient.totalFat, Math.toIntExact((Long) food.get("totalFat")));
            f.addNutrient(Nutrient.cholesterol, Math.toIntExact((Long) food.get("cholesterol")));
            f.addNutrient(Nutrient.sodium, Math.toIntExact((Long) food.get("sodium")));
            f.addNutrient(Nutrient.carbs, Math.toIntExact((Long) food.get("carbs")));
            f.addNutrient(Nutrient.protein, Math.toIntExact(((Long) food.get("protein"))));
            f.addNutrient(Nutrient.iron, Math.toIntExact((Long) food.get("iron")));
            f.addNutrient(Nutrient.potassium, Math.toIntExact((Long) food.get("potassium")));
            f.addNutrient(Nutrient.vitA, Math.toIntExact((Long) food.get("vitamin A")));
            f.addNutrient(Nutrient.vitC, Math.toIntExact((Long) food.get("vitamin C")));
            f.addNutrient(Nutrient.calcium, Math.toIntExact((Long) food.get("calcium")));
            foodToLoad.add(f);
        }
        return foodToLoad;
    }

    // MODIFIES: App
    // EFFECTS: returns an array of the existingFoods from the saved data file
    public ArrayList<Food> loadExistingFood() {
        JSONArray existingFoods = (JSONArray) jsonObject.get("existingFoods");
        return loadFoodObjects(existingFoods);
    }
}