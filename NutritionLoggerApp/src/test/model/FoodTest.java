package model;

import org.junit.jupiter.api.Test;

import static model.Nutrient.*;
import static org.junit.jupiter.api.Assertions.*;

// Tests for the Food class

class FoodTest {

    Food apple = new Food("apple");

    @Test

    public void testGetName() {
        assertEquals(apple.getName(), "apple");
    }

    @Test

    public void testCalories() {
        apple.addNutrient(calories, 130);
        assertEquals(130, apple.getNutrient(calories));
    }

    @Test

    public void testTotalFat() {
        apple.addNutrient(totalFat, 0);
        assertEquals(0, apple.getNutrient(totalFat));
    }

    @Test

    public void testCholesterol() {
        apple.addNutrient(cholesterol, 0);
        assertEquals(0, apple.getNutrient(cholesterol));
    }

    @Test

    public void testSodium() {
        apple.addNutrient(sodium, 0);
        assertEquals(0, apple.getNutrient(sodium));
    }

    @Test

    public void testCarbs() {
        apple.addNutrient(carbs, 34);
        assertEquals(34, apple.getNutrient(carbs));
    }

    @Test

    public void testProtein() {
        apple.addNutrient(protein, 1);
        assertEquals(1, apple.getNutrient(protein));
    }

    @Test

    public void testIron() {
        apple.addNutrient(iron, 1);
        assertEquals(1, apple.getNutrient(iron));
    }

    @Test

    public void testPotassium() {
        apple.addNutrient(potassium, 260);
        assertEquals(260, apple.getNutrient(potassium));
    }

    @Test

    public void testVitA() {
        apple.addNutrient(vitA, 2);
        assertEquals(2, apple.getNutrient(vitA));
    }

    @Test

    public void testVitC() {
        apple.addNutrient(vitC, 8);
        assertEquals(8, apple.getNutrient(vitC));
    }

    @Test

    public void testCalcium() {
        apple.addNutrient(calcium, 2);
        assertEquals(2, apple.getNutrient(calcium));
    }
}