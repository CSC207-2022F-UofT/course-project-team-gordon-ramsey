package business.rules;

import business.rules.api.APIReader;
import business.rules.dbs.*;
import business.rules.dps.*;
import business.rules.ui.AddGroceriesChangeEvent;
import business.rules.ui.AddToFavoritesChangeEvent;
import business.rules.ui.ChangeEvent;
import business.rules.ui.ClearGroceriesChangeEvent;
import business.rules.ui.RemixRecipeChangeEvent;
import business.rules.ui.SaveRecipeChangeEvent;
import business.rules.ui.UI;
import business.rules.ui.UI.FIELD_TYPE;
import business.rules.ui.UI.MODIFICATION_TYPE;
import entities.Recipe;
import entities.User;

public class Presenter {

    private UseCaseHandler uch;
    private UI ui;
    private RecipeDB rdb;
    private UserDB udb;
    private User active_user;
    private Recipe last_recipe;
    private Recipe[] last_viewed;

    /**
     * Sequentially builds the necessary elements of the presenter
     * @param ui UI with which to set initial view
     * @param usr SDR of UserDataPackets to read User data from local DB
     * @param usw SDW of UserDataPackets to write User data to local DB
     * @param rsr SDR of RecipeDataPackets to read Recipe data from local DB
     * @param rsw SDW of RecipeDataPackets to write Recipe data to local DB
     * @param api APIReader to pull JSON from API to DB for parsing
     * @return Returns the built, active presenter with all necessary components
     */
    public static Presenter buildPresenter(UI ui, SerializableDatabaseReader<UserDataPacket> usr,
                                           SerializableDatabaseWriter<UserDataPacket> usw,
                                           SerializableDatabaseReader<RecipeDataPacket> rsr,
                                           SerializableDatabaseWriter<RecipeDataPacket> rsw, APIReader api){
        Presenter p = new Presenter(ui, usr, usw, rsr, rsw, api);
        ui.setPresenter(p);
        api.setPresenter(p);
        p.initDatabases(usr, usw, rsr, rsw, api);
        if(p.ready())return p;
        return null;
    }

    /**
     *
     * @param ui UI to be used by this presenter
     * @param usr SDR of UserDataPackets to read User data from local DB
     * @param usw SDW of UserDataPackets to write User data to local DB
     * @param rsr SDR of RecipeDataPackets to read Recipe data from local DB
     * @param rsw SDW of RecipeDataPackets to write Recipe data to local DB
     * @param api APIReader to pull JSON from API to DB for parsing
     */
    private Presenter(UI ui, SerializableDatabaseReader<UserDataPacket> usr,
                      SerializableDatabaseWriter<UserDataPacket> usw,
                      SerializableDatabaseReader<RecipeDataPacket> rsr,
                      SerializableDatabaseWriter<RecipeDataPacket> rsw, APIReader api){
        this.uch = new UseCaseHandler(this);
        this.ui = ui;
        this.rdb = null;
        this.udb = null;
        this.active_user = null;
        this.last_recipe = null;
        this.last_viewed = null;
    }

    /**
     * Initializes necessary databases
     * @param usr SDR of UserDataPackets to initialize
     * @param usw SDW of UserDataPackets to initialize
     * @param rsr SDR of RecipeDataPackets to initialize
     * @param rsw SDW of RecipeDataPackets to initialize
     * @param api APIReader to initialize
     */
    private void initDatabases(SerializableDatabaseReader<UserDataPacket> usr,
                               SerializableDatabaseWriter<UserDataPacket> usw,
                               SerializableDatabaseReader<RecipeDataPacket> rsr,
                               SerializableDatabaseWriter<RecipeDataPacket> rsw, APIReader api){
        this.rdb = RecipeDB.getLocalInstance(rsr, rsw, api, this);
        this.udb = UserDB.getLocalInstance(usr, usw, this);
    }

    private boolean ready(){
        return this.rdb != null && this.udb != null && this.ui != null;
    }

    public void fireEvent(ChangeEvent e){
        if(e instanceof AddGroceriesChangeEvent){
            if(this.last_recipe == null || this.active_user == null){
                this.showUser("Adding Recipe to Grocery List failed : need both user and recipe.");
            }
            else this.uch.handle(new AddGroceriesChangeEvent(this.last_recipe, this.active_user));
        }
        else if(e instanceof AddToFavoritesChangeEvent){
            if(this.last_recipe == null || this.active_user == null){
                this.showUser("Adding Recipe to Favorites failed : need both user and recipe.");
            }
            else this.uch.handle(new AddToFavoritesChangeEvent(this.last_recipe, this.active_user));
        }
        else if(e instanceof SaveRecipeChangeEvent){
            if(this.last_recipe == null){
                this.showUser("Saving Recipe locally failed : need recipe to save.");
            }
            else this.uch.handle(new SaveRecipeChangeEvent(this.last_recipe));
        }
        else if(e instanceof RemixRecipeChangeEvent){
            if(this.last_recipe == null || this.active_user == null){
                this.showUser("Remixing Recipe failed : need both user and recipe.");
            }
            else this.uch.handle(new RemixRecipeChangeEvent(this.last_recipe, this.active_user));
        }
        else if(e instanceof ClearGroceriesChangeEvent){
            if(this.active_user == null){
                this.showUser("Clearing Grocery List failed : need user to clear grocery list of.");
            }
            else this.uch.handle(new ClearGroceriesChangeEvent(this.active_user));
        }
        else this.uch.handle(e);
    }

    /**
     * Passes UI a string to be displayed
     * @param str String to be displayed to user
     */
    public void showUser(String str){
        this.ui.showMessage(str);
    }

    /**
     * Passes UI a Recipe to be displayed in collection form
     * @param r Recipe to be displayed to user in collection form
     */
    public void showUser(Recipe r){
        this.ui.showCollection(r.getCollection());
    }
    /**
     * Passes UI an Array of Recipes to be displayed in collection form
     * @param r Recipes to be displayed to user in collection form
     */
    public void showUser(Recipe[] r){
        /*
         * POSTCONDITION: collection[i] = recipe[i] data
         */
        String[][][] collection = new String[r.length][][];
        for(int i = 0; i < collection.length; i++){
            collection[i] = r[i].getCollection();
        }
        this.last_viewed = r;
        this.ui.showCollection(collection);
    }

    public String[] askUserField(String[] field, MODIFICATION_TYPE mtype, FIELD_TYPE ftype){
        return this.ui.requestCollectionFieldModification(field, mtype, ftype);
    }

    public RecipeDB getRecipeDB(){
        return this.rdb;
    }
    /**
     * Passes the active UserDB
     * @return The active UserDB
     */
    public UserDB getUserDB(){
        return this.udb;
    }

    /**
     * Safely closes the active DBs
     */
    public void close(){
        this.rdb.close();
        this.udb.close();
    }

    /**
     * Sets active user
     * @param usr User to be set as active
     */
    public void setUser(User usr){
        this.active_user = usr;
    }

    /**
     * Passes the active User
     * @return Returns the active User
     */
    public User getUser(){
        return this.active_user;
    }

    public boolean hasActiveUser(){
        return !(this.active_user == null);
    }

    public void showLastSelection(){
        if(this.last_recipe == null) this.ui.showMessage("No recipe has been selected.");
        else this.ui.showCollection(this.last_recipe.getCollection());
    }

    /**
     * Displays the active User's favourite Recipes. If no User is active, displays a String notifying the user
     */
    public void showFavoriteRecipes(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to show favorite recipes.");
            return;
        }
        this.ui.showCollection(this.active_user.getJournal().getFavoritesCollection());
    }

    /**
     * Displays the active User's journal. If no User is active, displays a String notifying the user
     */
    public void showJournal(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to show journal.");
            return;
        }
        this.ui.showCollection(this.active_user.getJournal().getCollection());
    }

    /**
     * Passes the active User's Grocery List to the UI. If no User is active, displays a String notifying the user
     */
    public void showGroceryList(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to show grocery list.");
            return;
        }
        this.ui.showCollection(this.active_user.getGroceryList().getCollection());
    }

    public void showLocalRecipes(){
        this.ui.showCollection(this.rdb.getCollection());
    }

    public void logoutUser(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to logout.");
            return;
        }
        this.active_user = null;
        this.ui.showMessage("User logged out successfully.");
    }

    public void setRecipeSelection(int index){
        /**
         * PRECONDITION: active_user not null, index in range.
         */
        if(this.last_viewed == null){
            this.ui.showMessage("No active recipe results in view ! Unable to select recipe.");
            return;
        }
        else if(this.last_viewed[index] == null){
            this.ui.showMessage("Selected index has no recipe ! Unable to select recipe.");
            return;
        }
        this.last_recipe = this.last_viewed[index];
        this.ui.showMessage("Recipe selected successfully :");
        this.showUser(this.last_recipe);
    }

    public void start(){
        this.ui.run();
    }
}