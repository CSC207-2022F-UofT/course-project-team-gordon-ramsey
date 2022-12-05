package business.rules;

import business.rules.api.APIReader;
import business.rules.dbs.RecipeDB;
import business.rules.dbs.UserDB;
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
    private Recipe last_recipe;
    private Recipe[] last_viewed;
    
    public static Presenter buildPresenter(UI ui, APIReader api){
        Presenter p = new Presenter(ui, api);
        ui.setPresenter(p);
        api.setPresenter(p);
        return p;
    }

    private Presenter(UI ui, APIReader api){
        this.uch = new UseCaseHandler(this);
        this.rdb = new RecipeDB(api);
        this.ui = ui;
        this.active_user = null;
        this.last_recipe = null;
        this.last_viewed = null;
    }

    public void fireEvent(ChangeEvent e){
        this.uch.handle(e);
    }

    public void showUser(String str){
        this.ui.showMessage(str);
    }

    public void showUser(Recipe r){
        this.ui.showCollection(r.getCollection());
    }

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

    public RecipeDB getRecipeDB(){
        return this.rdb;
    }

    public UserDB getUserDB(){
        return this.udb;
    }
  
    public void close(){
        this.rdb.close();
    }

    public void setUser(User usr){
        this.active_user = usr;
    }

    public User getUser(){
        return this.active_user;
    }

    public boolean hasActiveUser(){
        return !(this.active_user == null);
    }

    public void showLastRecipe(){
        if(this.last_recipe == null) this.ui.showMessage("No recipe has been selected.");
        else this.ui.showCollection(this.last_recipe.getCollection());
    }

    public void showFavoriteRecipes(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to show favorite recipes.");
            return;
        }
        this.ui.showCollection(this.active_user.getJournal().getFavoritesCollection());
    }

    public void showJournal(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to show journal.");
            return;
        }
        this.ui.showCollection(this.active_user.getJournal().getCollection());
    }

    public void showGroceryList(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to show grocery list.");
            return;
        }
        this.ui.showCollection(this.active_user.getGroceryList().getCollection());
    }

    public void logoutUser(){
        if(this.active_user == null){
            this.ui.showMessage("No active user ! Unable to logout.");
            return;
        }
        this.active_user.saveChanges();
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
        this.ui.showMessage("Recipe " + (index + 1) + " selected successfully :");
        this.showUser(this.last_recipe);
    }

    public void start(){
        this.ui.run();
    }
}