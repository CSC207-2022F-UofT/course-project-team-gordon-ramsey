package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;
import java.util.Scanner;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;

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
        showMessage("Welcome to the recipe app!");
        while (true) {
            boolean success = identifyUser();
            if (quit) return;
            if (success) break;// FIXME
        }
        // proceed to the main menu (search, add recipe, etc.)
    }

    private boolean identifyUser(){
        showMessage("RECIPE APP");
        showMessage("type 'quit' to terminate the program.");
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

    private String chooseMenu(String[] menus){
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


    /**
     * start point of a search recipe process, data passed is single String, the search string.
     */

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }
}
