package external.interfaces;

import business.rules.Presenter;
import business.rules.ui.*;

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
                        //remix();
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
        String keyword = this.reader.nextLine();
        boolean verbose = true;  // ask user for verbose option.
        this.presenter.fireEvent(new RecipeSearchChangeEvent(keyword, verbose));
    }

    public void select(){
        Scanner reader = new Scanner(System.in);
        System.out.print("Choose recipe to select: ");
        String recipe = reader.nextLine();
        System.out.print("Add recipe to favourites?");
        String fav = reader.nextLine();
        boolean favourite;
        if (Objects.equals(fav, "Yes")){
            favourite = true;
        }
        else{
            favourite = false;
        }
        this.presenter.fireEvent(new SelectChangeEvent(recipe, favourite));
    }

    public void remix(String[][] recipe){
        System.out.print("Search For a Recipe to Remix");
        //add implemented search
        //Assign recipe to toRemix
        //Add Recipe id once RecipeDB is finished
        String[][] toRemix = recipe;
        String newName = null;
        String newDescription = null;
        String[][] newIngredients = null;
        String newInstructions = null;
        String newCookTime = null;
        String newYield = null;
        Scanner reader = new Scanner(System.in);
        boolean finishRemix = false;
        while (!finishRemix){
            System.out.print("What part of the recipe do you want to change?");
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
                    newIngredients = (String[][]) ingredients.toArray();
                    }
                    break;
                case "Instructions":
                        System.out.print("Enter instruction: ");
                        String instruction = reader.nextLine();
                        newInstructions = instruction;
                    break;
                case "Cooktime":
                    System.out.print("How many minutes does this recipe take?");
                    newCookTime = reader.nextLine();
                    break;
                case "Yield":
                    System.out.print("How many servings does this recipe yield?");
                    newYield = reader.nextLine();
                case "Finish Remix":
                    System.out.print("Finalizing remix");
                    finishRemix = true;
                    break;
                default:
                    finishRemix = true;
        this.presenter.fireEvent(new RemixChangeEvent(toRemix, newName,
                newDescription, newIngredients, newInstructions, newCookTime, newYield));
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

        //Fire ChangeEvent
        this.presenter.fireEvent(new CreateUserChangeEvent(username, password, fullname));

    }

    public void loginUser(){
        Scanner reader = new Scanner(System.in);

        //Collect user login info
        System.out.print("Enter username: ");
        String username = reader.nextLine();
        System.out.print("Enter password: ");
        String password = reader.nextLine();

        //UseCaseRequest parameters

        this.presenter.fireEvent(new LoginUserChangeEvent(username, password));

    }

    public void logoutUser(){
        Scanner reader = new Scanner(System.in);

        //Verify logout intent
        System.out.print("Logout now?");
        String confirmation = reader.nextLine();
        boolean confirmationBool = confirmation.equals("Yes");
        this.presenter.fireEvent(new LogoutChangeEvent(confirmationBool));
    }

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }

    public void showCollection(String[][] collec){
        /**
         * displaying a collection object, size of each subarray atleast 2.
         */
        String space;
        for(int i = 0; i < collec.length; i++){
            System.out.print(">> " + collec[i][0] + " : ");
            space = "";
            for(int k = 0; k < collec[i][0].length() + 6; k++) space += " ";
            if(collec[i].length >= 2) System.out.print(collec[i][1] + "\n");
            for(int j = 2; j < collec[i].length; j++){
                System.out.print(space + collec[i][j] + "\n");
            }
        }
    }

    @Override
    public void showCollection(String[][][] collec) {
        for(String[][] arr : collec){
            this.showCollection(arr);
        }
    }

    @Override
    public void run() {

    }

    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }
}