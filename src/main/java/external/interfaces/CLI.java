package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;

import java.util.Scanner;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;
    private String[] MENU_OPTIONS = {"search, remix, new_recipe, logout, quit"};

    public CLI(Presenter presenter){
        this.presenter = presenter;
        this.reader = new Scanner(System.in);
    }

    private void printMenu(String[] menus){
        showMessage("MENU");
        for (String menu : menus){
            System.out.print(menu + "\t");
        }
    }

    private String choice(String[] menus){
        while (true) {
            String input = this.reader.nextLine();
            if (valid(menus, input)) {
                return input;
            } else{
                showMessage("Error: Invalid command");
            }
        }
    }

    private boolean valid(String[] menus, String input){
        for (String menu : menus){
            if (input.equals(menu)){
                return true;
            }
        }
        return false;
    }

    public void menu(){
        while(true){
            String input = choice(MENU_OPTIONS);
            switch (input) {
                case "search":
                    search();
                    break;
                case "remix":
                    remix();
                    break;
                case "new_recipe":
                    newRecipe();
                    break;
                case "logout":
                    run();
                    break;
                case "quit":
                    this.reader.close();
                    System.exit(0);
            }
        }

    }

    private boolean identifyUser(){
        // merge with Jisu's part of CLI
        return true;
    }

    public void run(){
        System.out.println("Welcome to the recipe app!");
        while (true){
            if (identifyUser()){
                menu();
                break;
            }
        }
    }


    /**
     * start point of a search recipe process, data passed is single String, the search string.
     */
    public void search(){
        System.out.print("Enter Search Keywords: ");
        Object[] keyword = {null};
        keyword[0] = this.reader.nextLine();
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.SEARCH_RECIPE_USECASE, keyword));
    }

    public void remix(){
        System.out.print("Which recipe do you want to change: ");
        Object[] recipe = new Object[2];
        recipe[0] = this.reader.nextLine();
        recipe[1] = recipeInfo();
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.REMIX_RECIPE_USECASE, recipe));

    }

    public void newRecipe(){
        Object[] newRecipe = recipeInfo();
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.ADD_RECIPE_USECASE, newRecipe));
    }

    public Object[] recipeInfo() {
        Object[] info = new Object[5];
        System.out.print("Enter name: ");
        info[0] = this.reader.nextLine();
        System.out.print("Enter description: ");
        info[1] = this.reader.nextLine();
        System.out.print("Enter ingredients: ");
        info[2] = this.reader.nextLine();
        System.out.print("Enter instructions: ");
        info[3] = this.reader.nextLine();
        System.out.print("Enter cooking time: ");
        info[4] = this.reader.nextLine();

        return info;
    }

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }


}

