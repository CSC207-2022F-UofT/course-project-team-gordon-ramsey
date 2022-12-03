package business.rules;

import business.rules.api.APIReader;
import business.rules.dbs.RecipeDB;
import entities.Recipe;

public class Presenter {

    private UseCaseHandler uch;
    private UI ui;
    private RecipeDB rdb;

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

    public void close(){
        this.rdb.close();
    }
}