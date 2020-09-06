package ui;

import model.Day;
import model.Food;
import org.json.simple.parser.ParseException;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// GUI class that helps App to load saved data

public class Loader {

    private static final String foodFile = "./data/log.txt";
    private App app;

    public Loader(App app) {
        this.app = app;
    }


    // MODIFIES: app
    // EFFECTS: asks the user if they want to load saved data
    void askToLoad() {
        String answer = JOptionPane.showInputDialog("Press L to load saved data");
        if (answer != null && answer.equals("L")) {
            load();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads data from previous sessions
    void load() {
        try {
            loadFoodLog();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(app, "No saved data files");
        }
    }

    // MODIFIES: app
    // EFFECTS: loads data from previous session
    private void loadFoodLog() throws ParseException {
        try {
            Reader reader = new Reader(new File(foodFile));
            setExistingFoods(reader.loadExistingFood());
            setListOfDays(reader.loadDays());
            JOptionPane.showMessageDialog(app, "Loaded successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(app, "Error in retrieving data");
        }
    }

    // helper methods to assist in loading the saved data
    private void setExistingFoods(ArrayList<Food> foods) {
        app.existingFoods = foods;
    }

    private void setListOfDays(ArrayList<Day> days) {
        app.listOfDays = days;
    }

    // MODIFIES: ./data/log
    // EFFECTS: saves data from current session
    void save() {
        Writer writer = null;

        try {
            writer = new Writer(app.listOfDays, app.existingFoods, new File(foodFile));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(app, "Error. Wasn't able to save data successfully");
        }
        try {
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(app, "Error. Wasn't able to save data successfully");
        }
        JOptionPane.showMessageDialog(app, "Saved successfully");
    }
}
