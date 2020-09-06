# Nutrition App

## Personal Project by Shekinah Titus

Read to find out:
- *What* the application **does**
- *Who* will **use** it
- *Why* it's **interesting**

My nutrition app allows you to enter the food you consume, and keep track of nutritional value.
Anyone can benefit from this app as it motivates you to eat nutritious meals and be healthier.
My app is interesting because it allows you to compare your nutrition per day with the recommended amounts.
You can even add new food items, that are not already in the app, and their nutritional values.

## User Stories
- Create new food items and add them to today's log
- See which nutrients (and how much) still needs to be consumed today
- View daily food logs
- Compare today's nutrition log with the daily nutritional recommendation
- Save today's log to file when quitting the app
- Save nutritional values of food items
- Load past logs from file when the program starts

## Instructions for Grader
- You can generate the first required event by clicking the button "Add food"
- You can generate the second required event by clicking "Remaining"
- You can locate my visual component by clicking on "Compare" which shows you a table
- You can save the state of my application by clicking "Save"
- You can reload the state of my application by clicking "Load"

## Phase 4 Task 2
- Used map interface in the Food class

## Phase 4 Task 3

Two changes I made to improve my code design were
 1) To improve cohesion in the App class, I extracted a Loader class which is responsible for loading and saving data
 2) I also created a new class that implements ActionListener and took away that responsibility from the App class. This also increases cohesion.