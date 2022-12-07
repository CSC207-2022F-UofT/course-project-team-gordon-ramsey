package business.rules.base.response;

import entities.Recipe;

public class UseCaseRecipeListResponse extends UseCaseResponse{
    public Recipe[] recipes;

    public UseCaseRecipeListResponse(RETURN_CODE rCode, ACTION_CODE aCode, Recipe[] recipes){
        super(rCode, aCode);
        this.recipes = recipes;
    }
}
