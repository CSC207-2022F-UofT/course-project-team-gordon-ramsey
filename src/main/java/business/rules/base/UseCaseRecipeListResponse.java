package business.rules.base;

import entities.Recipe;

/**
 * Class representing the response of a recipe list from the UseCases
 */
public class UseCaseRecipeListResponse extends UseCaseResponse{
    public Recipe[] recipes;

    /**
     *
     * @param rCode return code of success/failure
     * @param aCode action code of show data recipe/string or do nothing
     * @param recipes list of recipes
     */
    public UseCaseRecipeListResponse(RETURN_CODE rCode, ACTION_CODE aCode, Recipe[] recipes){
        super(rCode, aCode);
        this.recipes = recipes;
    }
}
