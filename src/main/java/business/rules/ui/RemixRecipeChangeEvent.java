package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;
import entities.User;

public class RemixRecipeChangeEvent extends ChangeEvent{
    public Recipe original_recipe;
    public User user;

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