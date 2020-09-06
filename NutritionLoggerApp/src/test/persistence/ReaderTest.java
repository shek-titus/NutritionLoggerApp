package persistence;

// tests for the Reader class

import model.Food;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static model.Nutrient.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for Reader
public class ReaderTest {

    @Test
    public void testReader() {
        try {
            Reader reader = new Reader(new File("./data/TestReaderFile"));
            assertEquals(reader.loadDays().size(), 1);
            assertEquals(reader.loadDays().get(0).getTodaysFood().size(), 1);
            Food savedApple = reader.loadDays().get(0).getTodaysFood().get(0);
            assertEquals(savedApple.getName(), "apple");
            assertEquals(savedApple.getNutrient(calories), 130);
            assertEquals(savedApple.getNutrient(totalFat), 0);
            assertEquals(savedApple.getNutrient(cholesterol), 0);
            assertEquals(savedApple.getNutrient(sodium), 0);
            assertEquals(savedApple.getNutrient(carbs), 34);
            assertEquals(savedApple.getNutrient(protein), 1);
            assertEquals(savedApple.getNutrient(iron), 2);
            assertEquals(savedApple.getNutrient(potassium), 260);
            assertEquals(savedApple.getNutrient(vitA), 2);
            assertEquals(savedApple.getNutrient(vitC), 8);
            assertEquals(savedApple.getNutrient(calcium), 2);
            assertEquals(reader.loadExistingFood().size(), 1);
            assertEquals(reader.loadExistingFood().get(0).getName(), "apple");

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (ParseException e) {
            fail("ParseException should not have been thrown");
        }
    }

    // copied from tellerApp
    @Test
    void testIOException() {
        try {
            Reader reader = new Reader(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        } catch (ParseException e) {
            fail("ParseException should not have been thrown");
        }
    }
}