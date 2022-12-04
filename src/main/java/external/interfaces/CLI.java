package external.interfaces;

import business.rules.Presenter;
import business.rules.ui.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;
    private final String MENU_HEAD = "----------Menu----------\n\nSelect an option by typing the option number.\n",
                         MENU_PROMPT = "\nOption: ";
    private final String[] user_menu = {"Search Recipe", "Show Selection", "Show Favorites", "Show Journal", "Show Grocery List", "Logout", "Exit"},
                     login_menu = {"Login", "Signup", "Exit"},
                     selection_menu = {"Mark as Favorite", "Add to Grocery List", "Go Back"};

                           // back, next, find for group of results !

    public CLI(Presenter presenter){
        this.presenter = presenter;
        this.reader = null;
    }

    public void run(){
        /**
         * PRECONDITION: presenter not null.
         */
        System.out.println("Welcome to Recipe Selector !\n");
        boolean quit = false;
        this.reader = new Scanner(System.in);
        while(!quit){
            if(this.presenter.hasActiveUser()){
                int response = this.showMenu(user_menu);
                if(response == -1) continue;
                switch(response){
                    case 0:this.searchRecipe();
                           break;
                    case 1:this.presenter.showSelectedRecipe();
                           break;
                    case 2:this.presenter.showFavoriteRecipes();
                           break;
                    case 3:this.presenter.showJournal();
                           break;
                    case 4:this.presenter.showGroceryList();
                           break;
                    case 5:this.presenter.logoutUser();
                           break;
                    case 6:this.presenter.close();
                           quit = true;
                           break;
                }
            } else {
                int response = this.showMenu(login_menu);
                if(response == -1) continue;
                switch(response){
                    case 0:this.loginUser();
                           break;
                    case 1:this.createUser();
                           break;
                    case 2:this.presenter.close();
                           quit = true;
                           break;
                }
            }
        }
        this.reader.close();
    }

    public int showMenu(String[] menu){
        System.out.println(MENU_HEAD);
        for(int i = 0; i < menu.length; i++){
            System.out.println(i + ") " + menu[i]);
        }
        System.out.print(MENU_PROMPT);
        try{
            int input = reader.nextInt();
            if(input < 0 || input >= menu.length) throw new IllegalArgumentException();
            return input;
        } catch (InputMismatchException | IllegalArgumentException e){
            System.err.println("Please type in a valid option number !");
            return -1;
        }
    }

    public boolean showYesNoPrompt(String prefix){
        while(true){
            System.out.print(prefix + " (y/n): ");
            try{
                boolean input = reader.nextBoolean();
                return input;
            } catch (InputMismatchException e){
                System.err.println("Please type in a valid y/n response !");
            }
        }
    }

    private void searchRecipe(){
        System.out.println("Type in a few keywords to search for recipes!");
        System.out.print("Enter Search Keywords: ");
        String keyword = this.reader.nextLine();
        boolean verbose = this.showYesNoPrompt("Do you want status updates while searching?");
        this.presenter.fireEvent(new RecipeSearchChangeEvent(keyword, verbose));
    }

    private void loginUser(){
        System.out.println("Type in your username and password to login !");
        System.out.print("Enter Username: ");
        String name = this.reader.nextLine();
        System.out.print("Enter Password: ");
        String password = this.reader.nextLine();
        this.presenter.fireEvent(new LoginUserChangeEvent(name, password));
    }

    private void createUser(){
        System.out.println("Type in your fullname, and the username and password you would like for signing up!");
        System.out.print("Enter Fullname: ");
        String name = this.reader.nextLine();
        System.out.print("Enter Username: ");
        String username = this.reader.nextLine();
        System.out.print("Enter Password: ");
        String password = this.reader.nextLine();
        System.out.print("Confirm Password: ");
        String password_check = this.reader.nextLine();
        if(password.equals(password_check))this.presenter.fireEvent(new CreateUserChangeEvent(name, username, password));
        else System.out.println("The password could not be confirmed, try again later.");
    }

    /*
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
    */

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

    public void showCollection(String[][][] collec){
        /**
         * displaying a list of collection objects.
         */
        // todo
    }

    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }
}
