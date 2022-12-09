package business.rules.base.request;

import business.rules.dbs.RecipeDB;
import entities.Recipe;

/**
 * Manages request to save recipe
 */
public class UseCaseSaveRecipeRequest extends UseCaseRequest{
    public Recipe recipe;
    public RecipeDB rdb;

    /**
     * @param recipe concerned recipe to save.
     * @param rdb concerned recipedb to save in.
     * @param stage request stage.
     */
    public UseCaseSaveRecipeRequest(Recipe recipe, RecipeDB rdb, int stage){
        super(stage);
        this.recipe = recipe;
        this.rdb = rdb;
    }
}