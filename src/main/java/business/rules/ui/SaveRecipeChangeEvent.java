package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;

public class SaveRecipeChangeEvent extends ChangeEvent{
    public Recipe recipe;

    public SaveRecipeChangeEvent(){
        super(UseCaseHandler.USE_CASE.SAVE_RECIPE_USECASE);
        this.recipe = null;
    }

    public SaveRecipeChangeEvent(Recipe recipe){
        super(UseCaseHandler.USE_CASE.SAVE_RECIPE_USECASE);
        this.recipe = recipe;
    }
}