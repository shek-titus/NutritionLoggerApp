package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Nutrient.*;
import static org.junit.jupiter.api.Assertions.*;

// Tests for the Day class

class DayTest {

    Day today = new Day();
    Food apple;
    ArrayList<Food> foodList;

    @BeforeEach

    public void runBefore() {

        apple = new Food("apple");

        apple.addNutrient(calories, 130);
        apple.addNutrient(totalFat, 0);
        apple.addNutrient(cholesterol, 0);
        apple.addNutrient(sodium, 0);
        apple.addNutrient(carbs, 34);
        apple.addNutrient(protein, 1);
        apple.addNutrient(iron, 1);
        apple.addNutrient(potassium, 260);
        apple.addNutrient(vitA, 2);
        apple.addNutrient(vitC, 2);
        apple.addNutrient(calcium, 2);
    }

    @Test

    public void testAddFood() {
        today.addFood(apple);
        assertEquals(1, today.getTodaysFood().size());
        assertEquals(apple, today.getTodaysFood().get(0));
    }

    @Test

    public void testAddFoodMultiple() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(2, today.getTodaysFood().size());
        assertEquals(apple, today.getTodaysFood().get(0));
        assertEquals(apple, today.getTodaysFood().get(1));
    }

    @Test

    public void testGetTodaysNutrientNoFood() {
        assertEquals(today.getTodaysNutrient(calories), 0);
    }

    @Test

    public void testGetTodaysCalories() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(calories), apple.getNutrient(calories) * 2);
    }

    @Test

    public void testGetTodaysTotalFat() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(totalFat), apple.getNutrient(totalFat) * 2);
    }

    @Test

    public void testGetTodaysCholesterol() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(cholesterol), apple.getNutrient(cholesterol) * 2);
    }

    @Test

    public void testGetTodaysSodium() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(sodium), apple.getNutrient(sodium) * 2);
    }

    @Test

    public void testGetTodaysCarbs() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(carbs), apple.getNutrient(carbs) * 2);
    }

    @Test

    public void testGetTodaysProtein() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(protein), apple.getNutrient(protein) * 2);
    }

    @Test

    public void testGetTodaysIron() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(iron), apple.getNutrient(iron) * 2);
    }

    @Test

    public void testGetTodaysPotassium() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(potassium), apple.getNutrient(potassium) * 2);
    }

    @Test

    public void testGetTodaysVitA() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(vitA), apple.getNutrient(vitA) * 2);
    }

    @Test

    public void testGetTodaysVitC() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(vitC), apple.getNutrient(vitC) * 2);
    }

    @Test

    public void testGetTodaysCalcium() {
        today.addFood(apple);
        today.addFood(apple);
        assertEquals(today.getTodaysNutrient(calcium), apple.getNutrient(calcium) * 2);
    }

    private void initializeList() {
        foodList = new ArrayList<Food>();
        foodList.add(apple);
        foodList.add(apple);
    }
    @Test
    public void testSetTodaysFood() {
        initializeList();
        today.setTodaysFood(foodList);
        assertEquals(today.getTodaysFood().size(), 2);
        assertEquals(today.getTodaysFood().get(0), apple);
        assertEquals(today.getTodaysFood().get(1), apple);
    }
}