package model;

// Represents a food item and its nutritional contents

import java.util.HashMap;
import java.util.Map;

public class Food {

    private String name;
    Map<Nutrient, Integer> foodMap;

    public Food(String name) {
        this.name = name;
        foodMap = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: assigns the entered amount to the entered nutrient
    public void addNutrient(Nutrient nutrient, int amount) {
        foodMap.put(nutrient, amount);
    }


    // EFFECTS: returns the amount of given nutrient present in item
    public int getNutrient(Nutrient nutrient) {
        return foodMap.get(nutrient);
    }

}
