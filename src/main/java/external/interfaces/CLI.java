package external.interfaces;

import business.rules.Presenter;
import business.rules.ui.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;
    private final int DISPLAY_SIZE = 5;
    private final String MENU_HEAD = "\n------------ Menu ------------\n\nSelect an option by typing the option number.\n",
                         MENU_PROMPT = "\nOption: ",
                         DIVIDER = "------------------------------",
                         QUICK_PROMPT_HEAD = "\nSelect an option by typing the option character.\n",
                         QUICK_PROMPT_LIMITER = "    ",
                         QUICK_PROMPT_PROMPT = "\nOption: ";
    private final String[] user_menu = {"Search Recipe", "Show Last Selection", "Show Favorites", "Show Journal", "Show Grocery List", "Logout", "Exit"},
                     login_menu = {"Login", "Signup", "Exit"};

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
                    case 1:this.presenter.showLastRecipe();
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
            System.out.println((i + 1) + ") " + menu[i]);
        }
        System.out.print(MENU_PROMPT);
        try{
            int input = Integer.parseInt(this.showStringPrompt());
            if(input <= 0 || input > menu.length) throw new IllegalArgumentException();
            return input - 1;
        } catch (IllegalArgumentException e){
            System.err.println("Please type in a valid option number !");
            return -1;
        }
    }

    public boolean showYesNoPrompt(String prefix){
        while(true){
            System.out.print(prefix + " (y/n): ");
            try{
                boolean input = 'y' == Character.toLowerCase(this.showStringPrompt().charAt(0));
                return input;
            } catch (StringIndexOutOfBoundsException | InputMismatchException e){
                System.err.println("Please type in a valid y/n response !");
            }
        }
    }

    public String showStringPrompt(){
        String response = this.reader.nextLine();
        while(response.trim().length() == 0) response = this.reader.nextLine();
        return response;
    }

    private void searchRecipe(){
        System.out.println("Type in a few keywords to search for recipes!");
        System.out.print("Enter Search Keywords: ");
        String keyword = this.showStringPrompt();
        boolean verbose = this.showYesNoPrompt("Do you want status updates while searching?");
        this.presenter.fireEvent(new RecipeSearchChangeEvent(keyword, verbose));
        // ask if more loading ??
    }

    private void loginUser(){
        System.out.println("Type in your username and password to login !");
        System.out.print("Enter Username: ");
        String name = this.showStringPrompt();
        System.out.print("Enter Password: ");
        String password = this.showStringPrompt();
        this.presenter.fireEvent(new LoginUserChangeEvent(name, password));
    }

    private void createUser(){
        System.out.println("Type in your fullname, and the username and password you would like for signing up!");
        System.out.print("Enter Fullname: ");
        String name = this.showStringPrompt();
        System.out.print("Enter Username: ");
        String username = this.showStringPrompt();
        System.out.print("Enter Password: ");
        String password = this.showStringPrompt();
        System.out.print("Confirm Password: ");
        String password_check = this.showStringPrompt();
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
        System.out.println("Total number of items: " + collec.length);
        Pager pager = new Pager(collec, this.DISPLAY_SIZE);
        while(pager.inUse()){
            pager.promptMenu();
        }
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
        private boolean closed;
        private final String[] menu_items = {"[N]ext Page", "[L]ast Page", "[P]rint Page", "[J]ump to Page", "[S]elect Item", "[C]lose View"},
                               selection_menu_items = {"[A]dd to Journal", "[N]ote in Grocery List", "[G]o Back"};
        private final Character[] char_items = {'N', 'L', 'P', 'J', 'S', 'C'},
                             selection_char_items = {'A', 'N', 'G'};

        public Pager(String[][][] data, int page_size){
            /*
             * PRECONDITION: page_size > 0
             */
            this.data = data;
            this.page_size = page_size;
            this.start_index = 0;
            this.closed = this.data.length == 0;
        }
    
        public void printPage(){
            int end_index =  Math.min(start_index + page_size, data.length);
            showCollectionDivider("");
            for(int i = start_index; i < end_index; i++){
                showCollection(this.data[i]);
                showCollectionDivider("" + (i + 1));
            }
            showCollectionDivider("Page " + this.getPageNumber());
        }
    
        public void nextPage(){
            if(this.hasNextPage()){
                this.start_index += this.page_size;
                this.printPage();
            }
        }
    
        public boolean hasNextPage(){
            return this.start_index + this.page_size < this.data.length;
        }
    
        public void previousPage(){
            if(this.hasPreviousPage()){
                this.start_index -= this.page_size;
                this.printPage();
            }
        }
    
        public boolean hasPreviousPage(){
            return this.start_index - this.page_size >= 0;
        }
    
        public int getPageNumber(){
            return 1 + (this.start_index / this.page_size);
        }

        public int getMaxPageNumber(){
            return 1 + ((this.data.length - 1) / this.page_size);
        }

        public void promptMenu(){
            // could add find functionality.
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
                menu_buffer.add(this.menu_items[3]);
                char_buffer.add(this.char_items[3]);
                menu_buffer.add(this.menu_items[4]);
                char_buffer.add(this.char_items[4]);
            }
            menu_buffer.add(this.menu_items[5]);
            char_buffer.add(this.char_items[5]);
            String[] menu = new String[menu_buffer.size()];
            Character[] char_map = new Character[char_buffer.size()];
            for(int i = 0; i < menu.length; i++){
                menu[i] = menu_buffer.get(i);
                char_map[i] = char_buffer.get(i);
            }
            char response = showQuickPrompt(menu, char_map);
            switch(response){
                case 'N':this.nextPage();
                         break;
                case 'L':this.previousPage();
                         break;
                case 'P':this.printPage();
                         break;
                case 'J':this.jumpToPage();
                         break;
                case 'S':this.doSelection();
                         break;
                case 'C':this.close();
                         break;
            }
        }

        private void promptSelectionMenu(){
            boolean selected = true;
            while(selected){
                char response = showQuickPrompt(this.selection_menu_items, this.selection_char_items);
                switch(response){
                    case 'A':// add to journal
                             break;
                    case 'N':// add to grocery list
                             break;
                    case 'G':selected = false;
                             break;
                }
            }
        }

        private void doSelection(){
            System.out.print("Enter the item number to select: ");
            try{
                int input = reader.nextInt();
                if(input <= 0 || input > this.data.length) throw new IllegalArgumentException();
                presenter.setRecipeSelection(input - 1);
                this.promptSelectionMenu();
            } catch(InputMismatchException | IllegalArgumentException e){
                System.err.println("Please type in a valid item number !");
            }
        }

        private void jumpToPage(){
            System.out.print("Enter the page number to jump to: ");
            try{
                int input = reader.nextInt();
                if(input <= 0 || input > this.getMaxPageNumber()) throw new IllegalArgumentException();
                this.start_index = (input - 1) * this.page_size;
                this.printPage();
            } catch(InputMismatchException | IllegalArgumentException e){
                System.err.println("Please type in a valid page number !");
            }
        }

        public void close(){
            this.closed = true;
        }

        public boolean inUse(){
            return !this.closed;
        }
    }

}
