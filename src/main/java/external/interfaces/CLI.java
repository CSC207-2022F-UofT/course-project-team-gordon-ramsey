package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;
import business.rules.base.UseCaseRemixRequest;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;
    private String[] MENU_OPTIONS = {"search, remix, new_recipe, logout, quit"};

    private boolean quit = false;
    // FIXME think of the best way of implementing quit / logout
    // current idea: getUserInput() will switch it to true whenever user inputs "quit"
    // run() will check its value and quit if it's true

    private final String[] IDENTIFICATION_COMMANDS = {"login", "sign up"};

    // Allows the user to choose from a given list of commands.
    // returns null if the input is invalid


    public CLI(Presenter presenter){
        this.presenter = presenter;
        this.reader = new Scanner(System.in);
    }

    public void run(){
        // the outermost function (starting point)
        showMessage("RECIPE APP");
        showMessage("Welcome to the recipe app!");
        showMessage("type 'quit' to terminate the program.");
        while (true) {
            boolean success = identifyUser();
            if (quit) return;
            if (success) break;// FIXME
        }
        // proceed to the main menu (search, add recipe, etc.)
    }

    private boolean identifyUser(){
        showMessage("Please login or sign up to continue.");
        String command = chooseMenu(IDENTIFICATION_COMMANDS);
        switch (command) {
            case "login" :
                return login();
            case "sign up":
                return signup();
            default:
                invalidCommand(command);
                return false;
        }
    }

    private String chooseMenu(String[] menus) {
        printMenu(menus);
        String userInput = getUserInput();
        return validateChoice(menus, userInput);
    }

    private void printMenu(String[] menus){
        showMessage("MENU");
        for (String menu : menus){
            System.out.print(menu + "\t");
        }
    }

    public String getUserInput(){
        String input = reader.nextLine();
        if (input.equals("quit")){
            this.quit = true;
        }
        return input;
    }

    public boolean login(){
        showMessage("enter your username: ");
        String username = reader.nextLine();
        showMessage("enter your password: ");
        String password = reader.nextLine();
        String[] data = {username, password}; // FIXME is the data in an adequate format?
        ChangeEvent e = new ChangeEvent(USE_CASE.USER_LOGIN_USECASE, data);
        return true; // FIXME change the presenter so that it returns the result
        // return presenter.fireEvent(e);
        // assuming the presenter returns the result of the login
    }

    public boolean signup(){
        showMessage("enter your username: ");
        String username = reader.nextLine();
        showMessage("enter your password: ");
        String password = reader.nextLine();
        String[] data = {username, password}; // FIXME is the data in an adequate format?
        ChangeEvent e = new ChangeEvent(USE_CASE.CREATE_USER_USECASE, data);
        return true; // FIXME change the presenter so that it returns the result
        // return presenter.fireEvent(e);
        // assuming the presenter returns the result of the sign up
    }

    public void invalidCommand(String command){
        showMessage("Error: Invalid command: " + command);
    }

    private String validateChoice(String[] menus, String input){
        for (String menu : menus){
            if (input.equals(menu)){
                return input;
            }
        }
        return null;
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

    public void registerUser(){
        Scanner reader = new Scanner(System.in);

        //Collect necessary information from user
        System.out.print("Choose a username: ");
        String username = reader.nextLine();
        String password = null;
        boolean passwordConfirm = false;
        while (!passwordConfirm) {
            System.out.print("Choose a password: ");
            password = reader.nextLine();
            System.out.print("Confirm password: ");
            String passwordTwo = reader.nextLine();
            if (Objects.equals(password, passwordTwo)){
                passwordConfirm = true;
            }
        }
        System.out.print("Enter full name: ");
        String fullname = reader.nextLine();

        //UseCase parameters
        Object[] data = {username, password, fullname};

        //Fire ChangeEvent
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.CREATE_USER_USECASE, data));

    }

    public void loginUser(){
        Scanner reader = new Scanner(System.in);

        //Collect user login info
        System.out.print("Enter username: ");
        String username = reader.nextLine();
        System.out.print("Enter password: ");
        String password = reader.nextLine();

        //UseCaseRequest parameters
        Object[] data = {username, password};

        this.presenter.fireEvent(new ChangeEvent(USE_CASE.USER_LOGIN_USECASE, data));

    }

    public void logoutUser(){
        Scanner reader = new Scanner(System.in);

        //Verify logout intent
        System.out.print("Logout now?");
        String confirmation = reader.nextLine();
        Object[] data;
        if (confirmation.equals("Yes")){
            data = new Object[]{true};
        }
        else {
            data = new Object[]{false};
        }
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.USER_LOGOUT_USECASE, data));
    }

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }
}
