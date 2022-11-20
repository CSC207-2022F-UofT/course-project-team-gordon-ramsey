package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;

import java.util.Scanner;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;

    public CLI(Presenter presenter){
        this.presenter = presenter;
        this.reader = new Scanner(System.in);
    }

    public String getInput(){
        while(true){
            String input = this.reader.nextLine();
            if ("SearchRemixNew_recipeQuit".contains(input)){
                return input;
            }
            else{
                System.out.println("Invalid");
            }
        }

    }

    public void menu(){
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
                        this.reader.close();
                        System.exit(0);
                }

            System.out.print("\n");
        }

    }

    public void search(){
        System.out.print("Enter Search Keywords: ");
        Object[] keyword = {null};
        keyword[0] = this.reader.nextLine();
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.ADD_RECIPE_USECASE, keyword));
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
