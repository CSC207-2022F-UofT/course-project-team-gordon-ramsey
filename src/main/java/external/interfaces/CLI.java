package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;

import java.util.Scanner;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;

    private final String[] IDENTIFICATION_COMMANDS = {"login", "sign up"};

    // Allows the user to choose from a given list of commands.
    // returns null if the input is invalid
    private String chooseMenu(String[] menus){
        printMenu(menus);
        String userInput = reader.nextLine();
        return validateChoice(menus, userInput);
    }

    private void printMenu(String[] menus){
        showMessage("MENU");
        for (String menu : menus){
            System.out.print(menu + "\t");
        }
    }

    private String validateChoice(String[] menus, String userInput){
        boolean found = false;
        for (String menu : menus){
            if (menu.equals(userInput)){
                found = true;
                break;
            }
        }
        if (found) {
            return userInput;
        }
        else {
            return null;
        }
    }

    public CLI(Presenter presenter){
        this.presenter = presenter;
        this.reader = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Welcome to the recipe app!");
        identifyUser(); // FIXME
    }


    private boolean identifyUser(){

        // FIXME how can the UI know if the login/signup was successful?
        // (how can it break from the loop?)
        // should this be the presenter's job?
        showMessage("Please login or sign up to continue.");
        String command = chooseMenu(IDENTIFICATION_COMMANDS);
        switch (command) {
            case "login" :
                login();
                return true;
            case "sign up":
                signup();
                return true;
            default:
                invalidCommand();
                return false;
        }
    }

    public void login(){
        showMessage("enter your username: ");
        String username = reader.nextLine();
        showMessage("enter your password: ");
        String password = reader.nextLine();
        String[] data = {username, password};
        ChangeEvent e = new ChangeEvent(USE_CASE.USER_LOGIN_USECASE, data);
        presenter.fireEvent(e);
    }

    public void signup(){
        showMessage("enter your username: ");
        String username = reader.nextLine();
        showMessage("enter your password: ");
        String password = reader.nextLine();
        String[] data = {username, password};
        ChangeEvent e = new ChangeEvent(USE_CASE.CREATE_USER_USECASE, data);
        presenter.fireEvent(e);
    }

    public void invalidCommand(){
        showMessage("Error: Invalid command");
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

    /**
     * start point of a search recipe process, data passed is single String, the search string.
     */
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
