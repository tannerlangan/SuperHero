package com.SuperHeroList;

import com.google.gson.*;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/*Main class has 4 methods, 1 each for loading and exporting the superhero list to json, 1 to add superheroes to
* the array list and the main method which implements the menu.java file and superhero.java file.  The main method
* has if-else statements to navigate through the options and provides some functionality such as sorting the superhero
* list.*/

public class Main {

    public static ArrayList<SuperHero> superheroList = new ArrayList<>();
    private static ArrayList<SuperHero> heroListClone;


    //method used to load Superheroes from JSON file
    public static void loadSuperHeroes() {
        try {
            File input = new File("superhero.json");

            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonArray jsonArray = fileElement.getAsJsonArray();

            for (JsonElement heroElement : jsonArray) {

                JsonObject heroJsonObject = heroElement.getAsJsonObject();

                String name = heroJsonObject.get("name").getAsString();
                String superpower = heroJsonObject.get("superpower").getAsString();
                Double height = heroJsonObject.get("height").getAsDouble();
                Integer civiliansSaved = heroJsonObject.get("civiliansSaved").getAsInt();

                SuperHero newHero = new SuperHero(name, superpower, height, civiliansSaved);
                addSuperHero(newHero);


            }

        } catch (FileNotFoundException e) {
            System.out.println("No List of Superheroes found, please add some!");
        }
    }

    //method used GsonBuilder to convert superhero list to json and then export to file
    private static void loadHerosToJson(ArrayList<SuperHero> superheroList) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        for (int i = 0; i < superheroList.size(); i++) {


            try {
                FileWriter fos = new FileWriter("superhero.json");
                fos.write(gson.toJson(superheroList));
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


    public static void addSuperHero(SuperHero newHero) {

        superheroList.add(newHero);

    }


    public static void main(String[] arg) {
        //create objects for SuperHero, Scanner and Menu classes and call function to load existing superhero list if it exists
        Menu menu = new Menu();
        loadSuperHeroes();
        SuperHero superhero = new SuperHero();
        Scanner in = new Scanner(System.in);

        //declare choice variable that will be used to hold input for main menu
        int choice;

        //nested do while loop to validate inputs within the min and max range of 1 to 7
        do {
            do {
                //load main menu and take user input
                Menu.main();
                choice = in.nextInt();
            } while (choice < 0 || choice > 7);

            //if else statements containing the different options a user can choose
            //1. List all superheros currently stored in ArrayList
            //2. Add Superhero which creates a new object of Superhero type to hold information and then add it to arraylist
            //3. Remove superhero from ArrayList
            //4. Edit Superhero's amount of civilians saved and update in arraylist
            //5. List top 3 superheroes based on civilians saved, if there are lest than 3 heroes or less than 3 heroes with atleast 1 saved, used must add more heroes
            //6. Debug dump, calls on toString method in Superhero.java
            //7. Exit program

            if (choice == 1) {
                superhero.listHeroes(superheroList);
            } else if (choice == 2) {
                System.out.println("Please Enter Superheroes name");
                in.nextLine();
                String name = in.nextLine();
                System.out.println("Please enter their superpower");
                String power = in.nextLine();
                System.out.println("Please enter their height in cm");
                double height = in.nextDouble();
                if (height < 0) {
                    System.out.println("Please enter a valid height in cm");
                    height = in.nextDouble();
                }
                int civSaved = 0;
                SuperHero newHero = new SuperHero(name, power, height, civSaved);
                System.out.println(name + "has been added to the superhero list");
                addSuperHero(newHero);

            } else if (choice == 3) {
                superhero.listHeroes(superheroList);
                System.out.println("Please Select Superhero to be removed based on their ID or select 0 to return to main menu");
                int removeChoice = in.nextInt();
                if (removeChoice == 0) {
                    continue;
                }
                SuperHero currentHero = superheroList.get(removeChoice - 1);
                String heroName = currentHero.getName();
                superhero.removeHero(superheroList, removeChoice - 1, heroName);

            } else if (choice == 4) {
                superhero.listHeroes(superheroList);
                System.out.println("Please Select Superhero to be update based on their ID or press 0 to return to main menu");
                int updateChoice = in.nextInt();

                if (updateChoice == 0) {
                    continue;
                }

                if (updateChoice < 0) {
                    System.out.println("Please enter a ID starting at 1");
                    updateChoice = in.nextInt();
                }

                SuperHero currentHero = superheroList.get(updateChoice - 1);
                String heroName = currentHero.getName();
                int originalCivSaved = currentHero.getCiviliansSaved();
                System.out.println("How many civilians have they saved?");
                int newCivSaved = in.nextInt();
                superheroList.get((updateChoice - 1)).setCiviliansSaved(newCivSaved);
                System.out.println(heroName + " civilians saved has been updated to " + newCivSaved + " from " + originalCivSaved);


            } else if (choice == 5) {
                heroListClone = (ArrayList<SuperHero>) superheroList.clone();
                heroListClone.sort(Comparator.comparingInt(SuperHero::getCiviliansSaved).reversed());
                int count = 0;

                for (int i = 0; i < heroListClone.size(); i++) {
                    if (heroListClone.get(i).getCiviliansSaved() >= 1) {
                        count++;
                    }
                }
                if (heroListClone.size() < 3 || count < 3) {
                    System.out.println("Need More Heroes");

                } else {
                    for (int j = 0; j <= 2; j++) {
                        System.out.println(heroListClone.get(j));
                    }
                }

            } else if (choice == 6) {
                for(int k = 0; k < superheroList.size(); k++) {

                    System.out.println(superheroList.get(k));
                }
            }

        } while (choice != 7);

        //export the superhero list to json file, close scanner. May implement try-with-resources for better functionality to remove .inclose();
        loadHerosToJson(superheroList);
        in.close();


    }


}
