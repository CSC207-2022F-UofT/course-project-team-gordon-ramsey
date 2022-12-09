package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;

/**
 * A Class that changes the state of the presenter when the user saves a recipe.
 */
public class SaveRecipeChangeEvent extends ChangeEvent{
    public Recipe recipe;

    /*
     * utiltiy no argument constructor so that UI can initiate change event.
     */
    public SaveRecipeChangeEvent(){
        super(UseCaseHandler.USE_CASE.SAVE_RECIPE_USECASE);
        this.recipe = null;
    }

    /**
     * @param recipe recipe to save.
     */
    public SaveRecipeChangeEvent(Recipe recipe){
        super(UseCaseHandler.USE_CASE.SAVE_RECIPE_USECASE);
        this.recipe = recipe;
    }
}