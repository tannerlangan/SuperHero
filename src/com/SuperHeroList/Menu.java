package com.SuperHeroList;

public class Menu {

    static String[] options = {"List All Superheroes", "Add a New Super Hero", "Remove a superhero", "Update Number of Civilians Saved by a superhero"
            , "List Top 3 superheroes", "Debug Dump", "Exit"};
    static String menuTitle = "Superhero Tracker";

    public static void displayMenu() {


        for (int i = 0; i < options.length; i++) {
            int menuNum = i + 1;
            System.out.println(menuNum + ". " + options[i]);

        }

    }

    public static void printTitle() {
        for (int j = 0; j < menuTitle.length() + 4; j++) {
            if (j + 1 == menuTitle.length() + 4) {
                System.out.println("*");
            } else {
                System.out.print("*");
            }
        }
        System.out.println("  " + menuTitle);
        for (int j = 0; j < menuTitle.length() + 4; j++) {
            if (j + 1 == menuTitle.length() + 4) {
                System.out.println("*");
            } else {
                System.out.print("*");
            }
        }

    }

    public static void main() {

        printTitle();
        displayMenu();


    }
}
