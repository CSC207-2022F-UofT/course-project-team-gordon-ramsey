package external.interfaces;

import business.rules.Presenter;
import business.rules.ui.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;
    private static final int DISPLAY_SIZE = 5;
    private final String MENU_HEAD = "------------ Menu ------------\n\nSelect an option by typing the option number.\n",
                         MENU_PROMPT = "\nOption: ",
                         DIVIDER = "------------------------------",
                         QUICK_PROMPT_HEAD = "\nSelect an option by typing the option character.\n",
                         QUICK_PROMPT_LIMITER = "    ",
                         QUICK_PROMPT_PROMPT = "\nOption: ";
    private final String[] user_menu = {"Search Recipe", "Show Selection", "Show Favorites", "Show Journal", "Show Grocery List", "Logout", "Exit"},
                     login_menu = {"Login", "Signup", "Exit"},
                     selection_menu = {"Mark as Favorite", "Add to Grocery List", "Go Back"};

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
        System.out.println("Number of items: " + collec.length);

        // todo
        // previous, next, find, go back, jump for group of results !
    }

    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    public void showCollectionDivider(String desc){
        /*
         * PRECONDITION: desc.length + 2 <= divider.length
         */
        desc = " " + desc + " ";
        int index = (this.DIVIDER.length() - desc.length()) / 2;
        System.out.println(this.DIVIDER.substring(0, index) + desc + this.DIVIDER.substring(this.DIVIDER.length() - index));
    }

    public char showQuickPrompt(String[] menu, Character[] char_map){
        while(true){
            System.out.println(QUICK_PROMPT_HEAD);
            for(int i = 0; i < menu.length; i++){
                System.out.print(menu[i] + QUICK_PROMPT_LIMITER);
            }
            System.out.print("\n" + QUICK_PROMPT_PROMPT);
            try{
                char input = Character.toUpperCase(reader.next().charAt(0));
                for(char c : char_map){
                    if(c == input) return input;
                }
                throw new IllegalArgumentException();
            } catch(StringIndexOutOfBoundsException | IllegalArgumentException e){
                System.err.println("Please type in a valid option character !");
            }
        }
    }

    class Pager{
        private String[][][] data;
        private int page_size, start_index;
        private final String[] menu_items = {"[N]ext Page", "[L]ast Page", "[S]elect Item", "[C]lose View"};
        private final char[] char_items = {'N', 'L', 'S', 'C'};

        public Pager(String[][][] data, int page_size){
            this.data = data;
            this.page_size = page_size;
            this.start_index = 0;
        }
    
        public void printPage(){
            int end_index =  Math.min(start_index + page_size, data.length);
            for(int i = start_index; i < end_index; i++){
                showCollection(this.data[i]);
                showCollectionDivider("" + (i + 1));
            }
        }
    
        public void nextPage(){
            if(this.hasNextPage()) this.start_index += this.page_size;
        }
    
        public boolean hasNextPage(){
            return this.start_index + this.page_size < this.data.length;
        }
    
        public void previousPage(){
            if(this.hasPreviousPage()) this.start_index -= this.page_size;
        }
    
        public boolean hasPreviousPage(){
            return this.start_index - this.page_size >= 0;
        }
    
        public void promptMenu(){
            ArrayList<String> menu_buffer = new ArrayList<String>();
            ArrayList<Character> char_buffer = new ArrayList<Character>();
            if(this.hasNextPage()){
                menu_buffer.add(this.menu_items[0]);
                char_buffer.add(this.char_items[0]);
            }
            if(this.hasPreviousPage()){
                menu_buffer.add(this.menu_items[1]);
                char_buffer.add(this.char_items[1]);
            }
            if(this.data.length > 0){
                menu_buffer.add(this.menu_items[2]);
                char_buffer.add(this.char_items[2]);
            }
            menu_buffer.add(this.menu_items[3]);
            char_buffer.add(this.char_items[3]);
            String[] menu = (String[]) menu_buffer.toArray();
            Character[] char_map = (Character[]) char_buffer.toArray();
            showQuickPrompt(menu, char_map);
        }
    }

}
