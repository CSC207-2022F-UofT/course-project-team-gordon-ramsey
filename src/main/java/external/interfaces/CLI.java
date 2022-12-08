package external.interfaces;

import business.rules.Presenter;
import business.rules.ui.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

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
    private final String[] user_menu = {"Search Recipe", "Show Last Selection", "Show Favorites", "Show Journal", "Show Grocery List", "Show Local Recipes", "Logout", "Exit"},
                           modification_menu = {"[E]dit Field", "[A]dd to Field", "[R]emove from Field", "[V]iew all Fields", "[C]omplete Modification"},
                           login_menu = {"Login", "Signup", "Exit"};
    private final Character[] modification_char_map = {'E', 'A', 'R', 'V', 'C'};

    public CLI(Presenter presenter){
        this.presenter = presenter;
        this.reader = null;
    }

    public void run(){
        this.reader = new Scanner(System.in);
        boolean quit = false;
        while(!quit){
            if(this.presenter.hasActiveUser()){
                int response = this.showMenu(user_menu);
                if(response == -1) continue;
                switch(response){
                    case 0:this.searchRecipe();
                           break;
                    case 1:this.presenter.showLastSelection();
                           break;
                    case 2:this.presenter.showFavoriteRecipes();
                           break;
                    case 3:this.presenter.showJournal();
                           break;
                    case 4:this.presenter.showGroceryList();
                           break;
                    case 5:this.presenter.showLocalRecipes();
                           break;
                    case 6:this.presenter.logoutUser();
                           break;
                    case 7:this.presenter.close();
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
        }
        catch(IllegalArgumentException e){
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
            }
            catch(StringIndexOutOfBoundsException | InputMismatchException e){
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
    */

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }

    public void showCollection(String[][] collec){
        /**
         * displaying a collection object equivalent, size of each subarray atleast 2.
         */
        if(collec.length == 0){
            System.out.println("No item.");
            return;
        }
        String space;
        for(int i = 0; i < collec.length; i++){
            System.out.print(">> ");
            if(collec[i][0].length() > 0) System.out.print(collec[i][0] + " : ");
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
        if(collec.length == 0) System.out.println("There are no items.");
        else System.out.println("Total number of items: " + collec.length);
        Pager pager = new Pager(collec, this.DISPLAY_SIZE);
        while(pager.inUse()){
            pager.promptMenu();
        }
    }

    public String[] requestCollectionFieldModification(String[] field, MODIFICATION_TYPE mtype, FIELD_TYPE ftype){
        /*
         * PRECONDITION: field : collection object equivalent's field,
         *                       thus size is atleast one.
         */
        System.out.println("Please modify the following field by using provided options: " + field[0]);
        boolean complete = false;
        List<String> fields = new ArrayList<String>(Arrays.asList(field));
        while(!complete){
            char response = this.showModificationMenu(mtype);
            switch(response){
                case 'E':System.out.print("Enter the field number to edit: ");
                         break;
                case 'A':break;
                case 'R':break;
                case 'V':for(int i = 0; i < fields.size(); i++){
                             System.out.println(fields.get(i));
                         }
                         break;
                case 'C':complete = true; 
                         break;
            }
        }
        String[] new_field = new String[fields.size()];
        for(int i = 0; i < fields.size(); i++){
            new_field[i] = fields.get(i);
        }
        return new_field;
    }

    private char showModificationMenu(MODIFICATION_TYPE type){
        List<String> menu_buffer = new ArrayList<String>();
        List<Character> char_buffer = new ArrayList<Character>();
        if(type == MODIFICATION_TYPE.EDIT_VALUES || type == MODIFICATION_TYPE.EDIT_AND_ADD_REMOVE_VALUES){
            menu_buffer.add(this.modification_menu[0]);
            char_buffer.add(this.modification_char_map[0]);
        }
        if(type == MODIFICATION_TYPE.ADD_REMOVE_VALUES || type == MODIFICATION_TYPE.EDIT_AND_ADD_REMOVE_VALUES){
            menu_buffer.add(this.modification_menu[1]);
            char_buffer.add(this.modification_char_map[1]);
            menu_buffer.add(this.modification_menu[2]);
            char_buffer.add(this.modification_char_map[2]);
        }
        menu_buffer.add(this.modification_menu[3]);
        char_buffer.add(this.modification_char_map[3]);
        menu_buffer.add(this.modification_menu[4]);
        char_buffer.add(this.modification_char_map[4]);
        String[] menu = new String[menu_buffer.size()];
        Character[] char_map = new Character[char_buffer.size()];
        for(int i = 0; i < menu.length; i++){
            menu[i] = menu_buffer.get(i);
            char_map[i] = char_buffer.get(i);
        }
        return showQuickPrompt(menu, char_map);
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
                char input = Character.toUpperCase(showStringPrompt().charAt(0));
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
        private int[] sort_map;
        private Pager parent;
        private int page_size, start_index;
        private boolean closed;
        private final String[] menu_items = {"[N]ext Page", "[L]ast Page", "[P]rint Page", "[J]ump to Page", "[S]elect Item", "[F]ind Item", "[C]lose View"},
                               selection_menu_items = {"[M]ake Favorite", "[A]dd to Grocery List", "[R]emix & Favorite", "[S]ave Locally", "[P]rint Selection", "[G]o Back"};
        private final Character[] char_items = {'N', 'L', 'P', 'J', 'S', 'F', 'C'},
                                  selection_char_items = {'M', 'A', 'R', 'S', 'P', 'G'};

        public Pager(String[][][] data, int page_size){
            /*
             * PRECONDITION: page_size > 0
             */
            this.data = data;
            this.page_size = page_size;
            this.start_index = 0;
            this.closed = this.data.length == 0;
            this.parent = null;
            this.sort_map = null;
        }

        public Pager(String[][][] data, int page_size, Pager parent){
            /*
             * PRECONDITION: page_size > 0
             */
            this.data = data;
            this.page_size = page_size;
            this.start_index = 0;
            this.closed = this.data.length == 0;
            this.parent = parent;
            this.sort_map = null;
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
                menu_buffer.add(this.menu_items[5]);
                char_buffer.add(this.char_items[5]);
            }
            menu_buffer.add(this.menu_items[6]);
            char_buffer.add(this.char_items[6]);
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
                case 'F':this.findItems();
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
                    case 'M':presenter.fireEvent(new AddToFavoritesChangeEvent());
                             break;
                    case 'A':presenter.fireEvent(new AddGroceriesChangeEvent());
                             break;
                    case 'R':presenter.fireEvent(new RemixRecipeChangeEvent());
                             break;
                    case 'S':presenter.fireEvent(new SaveRecipeChangeEvent());
                             break;
                    case 'P':presenter.showLastSelection();
                             break;
                    case 'G':selected = false;
                             break;
                }
            }
        }

        private void findItems(){
            /*
             * compare search agianst collection[0][1], and sort, for new pager.
             */
            System.out.print("Enter keywords to search with: ");
            try{
                String input = showStringPrompt().trim();
                if(input.length() == 0) throw new IllegalArgumentException();
                String query = "";
                String[] keywords = input.split(" ");
                for(int i = 0; i < keywords.length; i++){
                    keywords[i] = keywords[i].toLowerCase();
                }
                List<Integer> matches = new ArrayList<Integer>();
                for(int i = 0; i < this.data.length; i++){
                    query = this.data[i][0][1];
                    for(String keyword : keywords){
                        if(query.toLowerCase().indexOf(keyword) >= 0){
                            matches.add(i);
                            break;
                        }
                    }
                }
                if(matches.size() == 0){
                    System.out.println("No matches found.");
                    return;
                }
                this.sort_map = new int[matches.size()];
                for(int i = 0; i < matches.size(); i++){
                    this.sort_map[i] = matches.get(i);
                }
                String[][][] new_data = new String[matches.size()][][];
                for(int i = 0; i < matches.size(); i++){
                    new_data[i] = this.data[matches.get(i)];
                }
                Pager p = new Pager(new_data, this.page_size, this);
                while(p.inUse()){
                    p.promptMenu();
                }
            } catch(IllegalArgumentException e){
                System.err.println("Please type in some search keywords !");
            }
        }

        private void doSelection(){
            System.out.print("Enter the item number to select: ");
            try{
                int input = Integer.parseInt(showStringPrompt());
                if(input <= 0 || input > this.data.length) throw new IllegalArgumentException();
                presenter.setRecipeSelection(this.getItemIndex(input - 1));
                this.promptSelectionMenu();
            } catch(IllegalArgumentException e){
                System.err.println("Please type in a valid item number !");
            }
        }

        private int getItemIndex(int input){
            if(this.parent == null) return input;
            else return this.parent.getItemIndex(this.parent.sort_map[input]);
        }

        private void jumpToPage(){
            System.out.print("Enter the page number to jump to: ");
            try{
                int input = Integer.parseInt(showStringPrompt());
                if(input <= 0 || input > this.getMaxPageNumber()) throw new IllegalArgumentException();
                this.start_index = (input - 1) * this.page_size;
                this.printPage();
            } catch(IllegalArgumentException e){
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

// TODO : confirm add grocery list works as intended, complete remix recipe use case.