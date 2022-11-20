package external.interfaces;

import business.rules.UI;

import java.util.Scanner;

public class CLI implements UI{

    public String getInput(){
        Scanner reader = new Scanner(System.in);

        while(true){
            String input = reader.nextLine();
            if ("SearchRemixNew_recipeQuit".contains(input)){
                reader.close();
                return input;
            }
            else{
                System.out.println("Invalid");
            }
        }

    }

    public void menu(){
        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.print("MENU \n");
            System.out.print("Search, Remix, New_recipe, Quit \n");
            String input = getInput();
                switch (input) {
                    case "Search":
                        search();
                        //trigger changeEvent?
                        break;
                    case "Remix":
                        remix();
                        //trigger changeEvent?
                        break;
                    case "New_recipe":
                        newRecipe();
                        //trigger changeEvent?
                        break;
                    case "Quit":
                        reader.close();
                        System.exit(0);
                }

            System.out.print("\n");
        }

    }

    public void search(){
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter Keyword: ");
        String word = reader.nextLine();
    }

    public void remix(){
        Scanner reader = new Scanner(System.in);
        System.out.print("What part of the recipe do you want to change?");
    }

    public void newRecipe(){
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = reader.nextLine();
        System.out.print("Enter description: ");
        String description = reader.nextLine();
        System.out.print("Enter ingredients: ");
        String ingredients = reader.nextLine();
        System.out.print("Enter instructions: ");
        String instructions = reader.nextLine();
        System.out.print("Enter cooking time: ");
        String cook_time = reader.nextLine();
    }

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }
}
