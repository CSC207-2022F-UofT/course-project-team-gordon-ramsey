package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;

import java.util.Objects;
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
        String newName = null;
        String newDescription = null;
        String[] newIngredients = null;
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
        //if confirm
        //UserLogoutUseCase parameters
        Object[] data = {true};

        this.presenter.fireEvent((new ChangeEvent(USE_CASE.USER_LOGOUT_USECASE, data)));
    }

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }
}
