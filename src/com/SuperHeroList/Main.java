package com.SuperHeroList;
import java.util.*;
import java.lang.*;



public class Main {

    public static ArrayList<SuperHero> superheroList = new ArrayList<>();
    private static ArrayList<SuperHero> heroListClone;


    public static void addSuperHero(SuperHero newHero){

        superheroList.add(newHero);

    }


    public static void main(String arg[]) {

        Scanner in = new Scanner(System.in);
        int choice;
        Menu menu = new Menu();
        SuperHero superhero = new SuperHero();
        do {
            do {
                menu.main();
                choice = in.nextInt();
            } while (choice < 0 || choice > 6);

            if(choice == 1){
                superhero.listHeroes(superheroList);
            }

            else if (choice == 2) {
                System.out.println("Please Enter Superheroes name");
                in.nextLine();
                String name = in.nextLine();
                System.out.println("Please enter their superpower");
                String power = in.next();
                System.out.println("Please enter their height in cm");
                double height = in.nextDouble();
                int civSaved = 0;
                SuperHero newHero = new SuperHero(name, power, height, civSaved);
                addSuperHero(newHero);
            }
            else if(choice == 3) {
                System.out.println("Please Select Superhero to be removed based on their ID");
                int removeChoice = in.nextInt();
                SuperHero currentHero = superheroList.get(removeChoice);
                String heroName = currentHero.getName();
                superhero.removeHero(superheroList, removeChoice, heroName);
            }
         else if (choice == 4) {
            superhero.listHeroes(superheroList);
            System.out.println("Please Select Superhero to be update based on their ID or press 0 to return to main menu");
            int updateChoice = in.nextInt();
            SuperHero currentHero = superheroList.get(updateChoice);
            String heroName = currentHero.getName();
            int originalCivSaved = currentHero.getCiviliansSaved();
            System.out.println("How many civilians have they saved?");
            int newCivSaved = in.nextInt();
            superheroList.get((updateChoice)).setCiviliansSaved(newCivSaved);
            System.out.println(heroName + " civilians saved has been updated to " + newCivSaved + " from " + originalCivSaved);


        } else if (choice == 5) {
            heroListClone = (ArrayList<SuperHero>) superheroList.clone();
            heroListClone.sort(Comparator.comparingInt(SuperHero::getCiviliansSaved).reversed());

            if(heroListClone.size() < 3){
             System.out.println("Need More Heroes");
            }
            else {
                for (int j = 0; j <= 2; j++) {
                    System.out.println(heroListClone.get(j));
                }
            }
}
        }while(choice != 0);

         in.close();


}
}