package model;

import java.util.ArrayList;

// Represents a Day. Keeps a list of food consumed on a day
// Contains methods to retrieve nutritional worth of the food in the list

public class Day {

    private ArrayList<Food> todaysFood;

    public Day() {
        this.todaysFood = new ArrayList<Food>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new food item to todaysFood list
    public void addFood(Food f) {
        todaysFood.add(f);
    }

    public void setTodaysFood(ArrayList<Food> todaysFood) {
        this.todaysFood = todaysFood;
    }

    public ArrayList<Food> getTodaysFood() {
        return todaysFood;
    }


    // EFFECTS: returns the amount of entered nutrient consumed today
    public int getTodaysNutrient(Nutrient nutrient) {
        int total = 0;
        for (Food f : todaysFood) {
            total = total + f.getNutrient(nutrient);
        }
        return total;
    }
}