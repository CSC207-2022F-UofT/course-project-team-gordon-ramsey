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
    
    public static Presenter buildPresenter(UI ui, SerializableDatabaseReader<UserDataPacket> usr,
                                           SerializableDatabaseWriter<UserDataPacket> usw,
                                           SerializableDatabaseReader<RecipeDataPacket> rsr,
                                           SerializableDatabaseWriter<RecipeDataPacket> rsw, APIReader api){
        Presenter p = new Presenter(ui, usr, usw, rsr, rsw, api);
        ui.setPresenter(p);
        api.setPresenter(p);
        return p;
    }

    private Presenter(UI ui, SerializableDatabaseReader<UserDataPacket> usr,
                      SerializableDatabaseWriter<UserDataPacket> usw,
                      SerializableDatabaseReader<RecipeDataPacket> rsr,
                      SerializableDatabaseWriter<RecipeDataPacket> rsw, APIReader api){
        this.uch = new UseCaseHandler(this);
        this.ui = ui;
        this.rdb = RecipeDB.getLocalInstance(rsr, rsw, api, this);
        this.udb = UserDB.getLocalInstance(usr, usw, this);
        this.active_user = null;
        this.selected_recipe = null;
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
        String[][][] collection = new String[r.length][][];
        for(int i = 0; i < collection.length; i++){
            collection[i] = r[i].getCollection();
        }
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

    public Recipe getSelectedRecipe(){
        return this.selected_recipe;
    }

    public boolean hasActiveUser(){
        return !(this.active_user == null);
    }

    public void showSelectedRecipe(){
        if(this.selected_recipe == null) this.ui.showMessage("No Recipe Selected.");
        else this.ui.showCollection(this.selected_recipe.getCollection());
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
}