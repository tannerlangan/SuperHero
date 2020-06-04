package com.SuperHeroList;

import java.util.ArrayList;

public class SuperHero{

    private String name, superpower;
    private  double height;
    private int civiliansSaved;

    public  SuperHero(String name, String superpower, double heightInCM, int civiliansSaved){
        this.name = name;
        this.superpower = superpower;
        this.height = heightInCM;
        this.civiliansSaved = civiliansSaved;
    }

    public  SuperHero(){
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Name: " + name + " Superpower: " + superpower + " Height: " + height + " Civilians Saved: " + civiliansSaved;
    }

    public void removeHero(ArrayList<SuperHero> heroList, int indexToBeRemoved, String superHero){

        heroList.remove(indexToBeRemoved);
        System.out.println(superHero + " has been removed from the Superhero List");
    }

    public void listHeroes(ArrayList<String> heroList){

        for(int i = 0; i < heroList.size(); i++){
            System.out.print("ID: " + i);
            System.out.println(heroList.get(i));

        }

    }


}
