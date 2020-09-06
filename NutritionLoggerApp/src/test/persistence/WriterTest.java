package persistence;

import model.Day;
import model.Food;
import model.Nutrient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static model.Nutrient.getNutrientList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for Writer

public class WriterTest {

    // some code is similar to TellerApp
    private static final String TEST_FILE = "./data/WriterTest.txt";
    private Writer testWriter;
    private Day testDay1;
    private Day testDay2;
    private Food apple;
    private Food banana;
    private Food milk;
    private ArrayList<Day> listOfDays;
    private ArrayList<Food> existingFood;

    @BeforeEach
    void runBefore() throws IOException {
        testDay1 = new Day();
        testDay2 = new Day();
        listOfDays = new ArrayList<Day>();
        listOfDays.add(testDay1);
        listOfDays.add(testDay2);
        existingFood = new ArrayList<Food>();
        createApple();
        createBanana();
        createMilk();
        testWriter = new Writer(listOfDays, existingFood, new File(TEST_FILE));
    }

    private void createApple() {
        apple = new Food("apple");
        apple.addNutrient(Nutrient.calories, 130);
        apple.addNutrient(Nutrient.totalFat, 0);
        apple.addNutrient(Nutrient.cholesterol, 0);
        apple.addNutrient(Nutrient.sodium, 0);
        apple.addNutrient(Nutrient.carbs, 34);
        apple.addNutrient(Nutrient.protein, 1);
        apple.addNutrient(Nutrient.iron, 2);
        apple.addNutrient(Nutrient.potassium, 230);
        apple.addNutrient(Nutrient.vitA, 2);
        apple.addNutrient(Nutrient.vitC, 30);
        apple.addNutrient(Nutrient.calcium, 2);
    }

    private void createBanana() {
        banana = new Food("banana");
        banana.addNutrient(Nutrient.calories, 130);
        banana.addNutrient(Nutrient.totalFat, 0);
        banana.addNutrient(Nutrient.cholesterol, 0);
        banana.addNutrient(Nutrient.sodium, 0);
        banana.addNutrient(Nutrient.carbs, 30);
        banana.addNutrient(Nutrient.protein, 1);
        banana.addNutrient(Nutrient.iron, 2);
        banana.addNutrient(Nutrient.potassium, 300);
        banana.addNutrient(Nutrient.vitA, 0);
        banana.addNutrient(Nutrient.vitC, 0);
        banana.addNutrient(Nutrient.calcium, 2);
    }

    private void createMilk() {
        milk = new Food("milk");
        milk.addNutrient(Nutrient.calories, 120);
        milk.addNutrient(Nutrient.totalFat, 5);
        milk.addNutrient(Nutrient.cholesterol, 2);
        milk.addNutrient(Nutrient.sodium, 2);
        milk.addNutrient(Nutrient.carbs, 30);
        milk.addNutrient(Nutrient.protein, 4);
        milk.addNutrient(Nutrient.iron, 2);
        milk.addNutrient(Nutrient.potassium, 0);
        milk.addNutrient(Nutrient.vitA, 30);
        milk.addNutrient(Nutrient.vitC, 0);
        milk.addNutrient(Nutrient.calcium, 30);
    }

    @Test

    public void testWriter() {
        // saving days and foods to file
        testDay1.addFood(apple);
        testDay1.addFood(banana);
        testDay2.addFood(milk);
        testDay2.addFood(banana);
        existingFood.add(apple);
        existingFood.add(banana);
        existingFood.add(milk);
        try {
            testWriter = new Writer(listOfDays, existingFood, new File(TEST_FILE));
            testWriter.close();
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
        // now reading to verify the saved values are correct

        try {
            Reader reader = new Reader(new File(TEST_FILE));
            assertEquals(reader.loadDays().size(), listOfDays.size());

            for (int i = 0; i < reader.loadDays().size(); i++) {
                assertEquals(reader.loadDays().get(i).getTodaysFood().size(), listOfDays.get(i).getTodaysFood().size());
            }

            for (int i = 0; i < reader.loadExistingFood().size(); i++) {
                assertEquals(reader.loadExistingFood().get(i).getName(), existingFood.get(i).getName());
                for (Nutrient n : getNutrientList()) {
                    assertEquals(reader.loadExistingFood().get(i).getNutrient(n), existingFood.get(i).getNutrient(n));
                }
            }
        } catch (ParseException e) {
            fail("ParseException should not have been thrown");
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}