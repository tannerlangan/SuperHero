package com.SuperHeroList;
import java.util.*;
import java.lang.*;


/*Most recent code finished:
    Implemented menu and 
 */


public class Main {

    public static ArrayList<SuperHero> superheroList = new ArrayList<>();


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
              for (int i = 0; i < superheroList.size(); i++){
                  System.out.println(superheroList.get(i).toString());
              }
            }

            else if (choice == 2) {
                System.out.println("Please Enter Superheroes name");
                String name = in.next();
                System.out.println("Please enter their superpower");
                String power = in.next();
                System.out.println("Please enter their height in cm");
                double height = in.nextDouble();
                System.out.println("Please enter how many civilians they have saved");
                int civSaved = in.nextInt();
                SuperHero newHero = new SuperHero(name, power, height, civSaved);
                addSuperHero(newHero);
            }
            else if(choice == 3){
                System.out.println("Please Select Superhero to be removed based on their ID");
                int removeChoice = in.nextInt();
                SuperHero currentHero = superheroList.get(removeChoice);
                String heroName = currentHero.getName();
                superhero.removeHero(superheroList, removeChoice, heroName);
            }
        }while(choice != 0);

         in.close();

    }
}