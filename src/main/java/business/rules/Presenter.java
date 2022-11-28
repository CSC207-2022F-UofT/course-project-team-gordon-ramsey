package business.rules;

import business.rules.api.APIReader;
import business.rules.dbs.RecipeDB;
import business.rules.dbs.UserDB;
import entities.Recipe;

public class Presenter {

    private UseCaseHandler uch;
    private UI ui;
    private RecipeDB rdb;

    private UserDB udb;

    public Presenter(UI ui, APIReader api){
        this.uch = new UseCaseHandler(this);
        this.rdb = new RecipeDB(api, this);
        this.ui = ui;
        ui.setPresenter(this);
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

    public UserDB getUserDB() { return this.udb;}
}