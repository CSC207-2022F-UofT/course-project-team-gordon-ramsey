package business.rules;

import business.rules.api.APIReader;
import business.rules.dbs.RecipeDB;
import business.rules.dbs.UserDB;
import entities.Recipe;
import entities.User;

public class Presenter {

    private UseCaseHandler uch;
    private UI ui;
    private RecipeDB rdb;
    private UserDB udb;
    private User active_user;
    private Recipe selected_recipe;
    
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
        this.selected_recipe = null;
    }

    public void fireEvent(ChangeEvent e){
        this.uch.handle(e.use_case_id, e.data);
    }

    public void showUser(String str){
        this.ui.showMessage(str);
    }

    public void showUser(Recipe r){
        this.ui.showCollection(r.getCollection());
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

    public void showSelectedRecipe(){
        if(this.selected_recipe == null) this.ui.showMessage("No Recipe Selected.");
        else this.ui.showCollection(this.selected_recipe.getCollection());
    }

    public void showFavoriteRecipes(){
        // todo
    }

    public void showJournal(){
        // todo
    }

    public void showGroceryList(){
        // todo
    }

    public void logoutUser(){
        // todo
    } 
}