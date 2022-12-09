package business.rules;

import business.rules.api.APIReader;
import business.rules.dbs.*;
import business.rules.dps.*;
import business.rules.ui.ChangeEvent;
import business.rules.ui.UI;
import entities.Recipe;
import entities.User;

public class Presenter {

    private UseCaseHandler uch;
    private UI ui;
    private RecipeDB rdb;
    private UserDB udb;
    private User active_user;
    private Recipe selected_recipe;

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
        return p;
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
        this.selected_recipe = null;
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

    /**
     * Fires ChangeEvent to UseCaseHandler to be processed
     * @param e ChangeEvent containing information needed from Presenter for UseCases
     */
    public void fireEvent(ChangeEvent e){
        this.uch.handle(e);
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
        String[][][] collection = new String[r.length][][];
        for(int i = 0; i < collection.length; i++){
            collection[i] = r[i].getCollection();
        }
        this.ui.showCollection(collection);
    }

    /**
     * Passes the active RecipeDB
     * @return The active RecipeDB
     */
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

    /**
     * Passes the currently selected Recipe
     * @return The selected Recipe
     */
    public Recipe getSelectedRecipe(){
        return this.selected_recipe;
    }

    /**
     * Returns if there is an active User
     * @return True if there is an active user, False otherwise
     */
    public boolean hasActiveUser(){
        return !(this.active_user == null);
    }

    /**
     * Displays the selected Recipe to the UI. If no recipe is selected, displays a String notifying the user
     *
     */
    public void showSelectedRecipe(){
        if(this.selected_recipe == null) this.ui.showMessage("No Recipe Selected.");
        else this.ui.showCollection(this.selected_recipe.getCollection());
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

    /**
     * Logs out the Active user and displays success. If no User is active, notifies user via String.
     */
    public void logoutUser(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to logout.");
            return;
        }
        this.active_user.saveChanges();
        this.active_user = null;
        this.ui.showMessage("User logged out successfully.");
    }
}