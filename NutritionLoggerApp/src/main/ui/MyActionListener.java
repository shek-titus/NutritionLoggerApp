package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ActionListener class for App
public class MyActionListener implements ActionListener {

    App app;

    public MyActionListener(App app) {
        this.app = app;
    }

    // EFFECTS: calls the correct method based on the user input
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("New day")) {
            app.addNewDay();
        } else if (action.equals("Save")) {
            app.loader.save();
        } else if (action.equals("Load")) {
            app.loader.load();
        } else if (action.equals("Add food")) {
            app.addNewFood();
        } else if (action.equals("Compare")) {
            app.nutrients();
        } else if (action.equals("Remaining")) {
            app.nutrientsLeft();
        } else if (action.equals("Today's log")) {
            app.todaysLog();
        } else if (action.equals("Quit")) {
            JOptionPane.showMessageDialog(app, "Goodbye!");
            app.dispose();
        } else if (action.equals("Back")) {
            app.goToHomePanel();
        }
    }
}
