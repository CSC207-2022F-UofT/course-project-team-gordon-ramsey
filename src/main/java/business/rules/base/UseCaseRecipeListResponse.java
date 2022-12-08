package business.rules.base;

import entities.Recipe;

/**
 *
 */
public class UseCaseRecipeListResponse extends UseCaseResponse{
    public Recipe[] recipes;

    /**
     *
     * @param rCode
     * @param aCode
     * @param recipes
     */
    public UseCaseRecipeListResponse(RETURN_CODE rCode, ACTION_CODE aCode, Recipe[] recipes){
        super(rCode, aCode);
        this.recipes = recipes;
    }
}
