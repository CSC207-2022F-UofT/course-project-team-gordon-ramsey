package business.rules.base.request;

import business.rules.dbs.RecipeDB;
import entities.Recipe;

public class UseCaseSaveRecipeRequest extends UseCaseRequest{
    public Recipe recipe;
    public RecipeDB rdb;

    public UseCaseSaveRecipeRequest(Recipe recipe, RecipeDB rdb, int stage){
        super(stage);
        this.recipe = recipe;
        this.rdb = rdb;
    }
}