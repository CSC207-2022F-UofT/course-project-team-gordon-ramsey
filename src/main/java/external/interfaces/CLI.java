package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;

import java.util.ArrayList;
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
        //Add Recipe id once RecipeDB is finished
        Object toRemix = null;
        String newName = null;
        String newDescription = null;
        Object[] newIngredients = null;
        Object[] newInstructions = null;
        String newCookTime = null;
        Scanner reader = new Scanner(System.in);
        System.out.print("What part of the recipe do you want to change?");
        boolean finishRemix = false;
        while (!finishRemix){
            System.out.print("Name, Description, Ingredients, Instructions, CookTime, Finish Remix");
            String input = reader.nextLine();
            switch (input){
                case "Name":
                    System.out.print("Enter new name: ");
                    newName = reader.nextLine();
                    System.out.print("Name set to " + newName);
                    break;
                case "Description":
                    System.out.print("Enter new description: ");
                    newDescription = reader.nextLine();
                    System.out.print("Name set to " + newDescription);
                    break;
                case "Ingredients":
                    boolean finishedIngredients = false;
                    ArrayList<String[]> ingredients = null;
                    while(!finishedIngredients){
                        System.out.print("Enter ingredient: ");
                        String ingredient = reader.nextLine();
                        System.out.print("Enter quantity: ");
                        String quantity = reader.nextLine();
                        System.out.print("Enter units: ");
                        String units = reader.nextLine();
                        ingredients.add(new String[]{ingredient, quantity, units});
                        System.out.print("Add another ingredient?");
                        String confirmation = reader.nextLine();
                        if (!confirmation.equals("Yes")) {
                            finishedIngredients = true;
                        }
                    newIngredients = ingredients.toArray();
                    }
                    break;
                case "Instructions":
                    boolean finishedInstructions = false;
                    ArrayList<String[]> instructions = null;
                    while (!finishedInstructions){
                        System.out.print("Enter instruction: ");
                        String instruction = reader.nextLine();
                        instructions.add(new String[]{instruction});
                        System.out.print("Add another instruction?");
                        String confirmation = reader.nextLine();
                        if (!confirmation.equals("Yes")){
                            finishedInstructions = true;
                        }
                    newInstructions = instructions.toArray();
                    }
                    break;
                case "Cooktime":
                    System.out.print("How many minutes does this recipe take?");
                    newCookTime = reader.nextLine();
                    break;
                case "Finish Remix":
                    System.out.print("Finalizing remix");
                    finishRemix = true;
                    break;
        //Object[] data = {toRemix, newName, newDescription, newIngredients, newInstructions, newCookTime};
        //this.presenter.fireEvent(new ChangeEvent(USE_CASE.REMIX_RECIPE_USECASE, data));
            }
        }

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
