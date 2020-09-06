package model;

// Represents a nutrient

import java.util.ArrayList;

public class Nutrient {

    private String name;
    private int amount;
    private String units;

    public Nutrient(String name, int amount, String units) {
        this.name = name;
        this.amount = amount;
        this.units = units;
    }

    public String getNutrientName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getUnits() {
        return this.units;
    }

    //Declaring constant nutrients and their recommended daily values
    public static final Nutrient calories = new Nutrient("calories", 2000, "kcal");
    public static final Nutrient totalFat = new Nutrient("total fat", 65, "g");
    public static final Nutrient cholesterol = new Nutrient("cholesterol", 300, "mg");
    public static final Nutrient sodium = new Nutrient("sodium", 2400, "mg");
    public static final Nutrient carbs = new Nutrient("carbs", 300, "g");
    public static final Nutrient protein = new Nutrient("protein", 50, "g");
    public static final Nutrient iron = new Nutrient("iron", 100, "%");
    public static final Nutrient potassium = new Nutrient("potassium", 4700, "mg");
    public static final Nutrient vitA = new Nutrient("vitamin A", 100, "%");
    public static final Nutrient vitC = new Nutrient("vitamin C", 100, "%");
    public static final Nutrient calcium = new Nutrient("calcium", 100, "%");

    // EFFECTS: returns an arraylist of all the nutrients
    public static ArrayList<Nutrient> getNutrientList() {
        ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();
        nutrientList.add(calories);
        nutrientList.add(totalFat);
        nutrientList.add(cholesterol);
        nutrientList.add(sodium);
        nutrientList.add(carbs);
        nutrientList.add(protein);
        nutrientList.add(iron);
        nutrientList.add(potassium);
        nutrientList.add(vitA);
        nutrientList.add(vitC);
        nutrientList.add(calcium);
        return nutrientList;
    }
}