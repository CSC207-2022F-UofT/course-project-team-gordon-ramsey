package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;
import entities.User;

/**
 * A Class that changes the state of the presenter when the user wants to remix a recipe
 */
public class RemixRecipeChangeEvent extends ChangeEvent{
    public Recipe original_recipe;
    public User user;

    /*
     * utiltiy no argument constructor so that UI can initiate change event.
     */
    public RemixRecipeChangeEvent(){
        super(UseCaseHandler.USE_CASE.REMIX_RECIPE_USECASE);
        this.original_recipe = null;
        this.user = null;
    }

    public RemixRecipeChangeEvent(Recipe original_recipe, User user){
        super(UseCaseHandler.USE_CASE.REMIX_RECIPE_USECASE);
        this.original_recipe = original_recipe;
        this.user = user;
    }
}