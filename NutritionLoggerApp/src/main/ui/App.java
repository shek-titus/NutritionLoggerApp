package ui;

import model.Day;
import model.Food;
import model.Nutrient;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import static model.Nutrient.*;

// Initializes the GUI and contains methods that make user stories possible

public class App extends JFrame {
    protected ArrayList<Day> listOfDays = new ArrayList<>();
    protected ArrayList<Food> existingFoods = new ArrayList<>();
    protected Day today;
    protected JPanel homePanel = new JPanel();
    protected ArrayList<JButton> buttonList;
    protected JPanel otherPanel;
    protected JButton back = new JButton("Back");
    protected Loader loader = new Loader(this);
    protected ActionListener al = new MyActionListener(this);

    // GUI details from space invaders and phase 3 example
    // Constructor from TellerApp
    // MODIFIES: this
    // EFFECTS: Starts the app and UI
    public App() {
        super("Nutritional Logger");
        initialize();
        loader.askToLoad();
        if (listOfDays.size() > 0) {
            today = listOfDays.get(listOfDays.size() - 1);
        } else {
            addNewDay();
        }
        createButtons();
        displayMenu();
    }

    // MODIFIES: this
    // EFFECTS: initializes the JFrame
    protected void initialize() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(scrn.width / 2, scrn.height / 2);
        setLocation(0, 0);
        setPreferredSize(windowSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    //EFFECTS: creates buttons
    protected void createButtons() {
        buttonList = new ArrayList<>();
        JButton day = new JButton("New day");
        buttonList.add(day);
        JButton load = new JButton("Load");
        buttonList.add(load);
        JButton save = new JButton("Save");
        buttonList.add(save);
        JButton addFood = new JButton("Add food");
        buttonList.add(addFood);
        JButton compare = new JButton("Compare");
        buttonList.add(compare);
        JButton left = new JButton("Remaining");
        buttonList.add(left);
        JButton todaysLog = new JButton("Today's log");
        buttonList.add(todaysLog);
        JButton quit = new JButton("Quit");
        buttonList.add(quit);
    }

    // MODIFIES: this
    //EFFECTS: lays out the buttons on the home screen
    protected void displayMenu() {
        for (JButton b : buttonList) {
            b.setActionCommand(b.getText());
            b.setPreferredSize(new Dimension(100, 60));
            b.addActionListener(al);
            b.setVisible(true);
            homePanel.add(b);
        }
        homePanel.setLayout(new GridLayout());
        add(homePanel);
        homePanel.revalidate();
    }

    // REQUIRES: JFrame is showing otherPanel
    // MODIFIES: this
    // EFFECTS: removes otherPanel and adds homePanel to the jFrame
    protected void goToHomePanel() {
        remove(otherPanel);
        add(homePanel);
        homePanel.revalidate();
        pack();
        homePanel.setVisible(true);
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: initializes the back Jbutton
    protected void initializeBackButton() {
        back.setActionCommand(back.getText());
        back.setPreferredSize(new Dimension(100, 60));
        back.addActionListener(al);
        back.setVisible(true);
        otherPanel.add(back);
    }

    private void addOtherPanelToFrame() {
        add(otherPanel);
        otherPanel.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: adds a new day to the list of days
    protected void addNewDay() {
        Day today = new Day();
        listOfDays.add(today);
        this.today = today;
        JOptionPane.showMessageDialog(this, "It's a new day!");
    }

    //REQUIRES: a string that is the name of a food item
    //MODIFIES: this, the food item
    //EFFECTS: adds given food item to today's log,
    //       if food item doesn't already exist, it creates a new food item with the given name, and adds it to the log
    protected void addNewFood() {
        String foodItem = JOptionPane.showInputDialog("What is the name of the food item you would like to add?");
        if (!(foodItem.equals(""))) {
            if (existingFoods.size() != 0) {
                for (Food f : existingFoods) {
                    if (f.getName().equals(foodItem)) {
                        today.addFood(f);
                        return;
                    }
                }
            }
            createNewFoodItem(foodItem);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new food item and adds it to todays log, and the list of existing foods
    protected void createNewFoodItem(String name) {
        Food food = new Food(name);
        itemNutrients(food);
        today.addFood(food);
        existingFoods.add(food);
    }

    // REQUIRES: a valid food object f
    // MODIFIES: the food object
    // EFFECTS: assigns nutrient values to the food object
    protected void itemNutrients(Food f) {
        for (Nutrient n : getNutrientList()) {
            String message = "How much " + n.getNutrientName() + "(" + n.getUnits() + ") ?";
            while (!(f.getNutrient(n) >= 0)) {
                try {
                    String input = JOptionPane.showInputDialog(message);
                    int amount = Integer.parseInt(input);
                    f.addNutrient(n, amount);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Please try again");
                }
            }
        }
    }

    // MODIFIES: this
    //EFFECTS: displays today's log
    protected void todaysLog() {
        remove(homePanel);
        otherPanel = new JPanel();
        DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);
        for (Food food : today.getTodaysFood()) {
            listModel.addElement(food.getName());
        }
        otherPanel.add(list);
        initializeBackButton();
        addOtherPanelToFrame();
    }

    // MODIFIES: this;
    //EFFECTS: displays a table of all the nutrients, how much of each has been consumed, and the recommended amount
    protected void nutrients() {
        remove(homePanel);
        otherPanel = new JPanel();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nutrient");
        model.addColumn("Units");
        model.addColumn("Amount Consumed");
        model.addColumn("Requirement");

        for (Nutrient n : getNutrientList()) {
            String name = n.getNutrientName();
            String units = n.getUnits();
            int amount = today.getTodaysNutrient(n);
            int requirement = n.getAmount();
            model.addRow(new Object[]{name, units, amount, requirement});
        }
        JTable nutrientTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(nutrientTable);
        otherPanel.add(scrollPane);
        initializeBackButton();
        addOtherPanelToFrame();
    }

    // MODIFIES: this
    //EFFECTS: displays a table of all the nutrients, and how much of each is yet to be consumed today
    protected void nutrientsLeft() {
        remove(homePanel);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nutrient");
        model.addColumn("Units");
        model.addColumn("Amount Remaining");
        for (Nutrient n : getNutrientList()) {
            String name = n.getNutrientName();
            String units = n.getUnits();
            int amount = today.getTodaysNutrient(n);
            int requirement = n.getAmount();
            int difference = requirement - amount;
            model.addRow(new Object[]{name, units, difference});
        }
        otherPanel = new JPanel();
        JTable nutrientTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(nutrientTable);
        otherPanel.add(scrollPane);
        initializeBackButton();
        addOtherPanelToFrame();
    }
}