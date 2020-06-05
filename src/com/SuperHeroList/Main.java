package com.SuperHeroList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {

    public static ArrayList<SuperHero> superheroList = new ArrayList<>();
    private static ArrayList<SuperHero> heroListClone;

    public static void loadSuperHeroes() {
        try {
            File input = new File("superhero.json");

            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray jsonArray = fileObject.get("Superhero").getAsJsonArray();

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

    public static void addSuperHero(SuperHero newHero) {

        superheroList.add(newHero);

    }


    public static void main(String[] arg) {
        loadSuperHeroes();
        Scanner in = new Scanner(System.in);
        int choice;
        Menu menu = new Menu();
        SuperHero superhero = new SuperHero();
        do {
            do {
                Menu.main();
                choice = in.nextInt();
            } while (choice < 0 || choice > 6);

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

                for(int i = 0; i < heroListClone.size();i++){
                    if(heroListClone.get(i).getCiviliansSaved() >= 1){
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
                System.out.println(superheroList);
            }

        } while (choice != 0);
        loadHerosToJson(superheroList);
        in.close();


    }


    private static void loadHerosToJson(ArrayList<SuperHero> superheroList) {

        try {
            File export = new File("superhero.json");
            if (export.createNewFile()) {
                System.out.println("File created: " + export.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file");
            e.printStackTrace();
        }

        for (int i = 0; i < superheroList.size(); i++) {
            FileWriter myWriter = null;
            try {
                myWriter = new FileWriter("superhero.json");
                myWriter.append(superheroList.toString());
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
