package model;


import org.junit.jupiter.api.Test;

import static model.Nutrient.calories;
import static org.junit.jupiter.api.Assertions.*;

// tests for Nutrient class
public class NutrientTest {

    // Nutrients are implicitly tested in the FoodTest and DayTest Classes

    // simple tests for getters

    @Test

    public void testGetUnits() {
        assertEquals(calories.getUnits(), "kcal");
    }

    @Test
    public void testGetAmount() {
        assertEquals(calories.getAmount(), 2000);
    }

    @Test
    public void testGetNutrientList() {
        assertEquals(Nutrient.getNutrientList().size(), 11);
    }

}