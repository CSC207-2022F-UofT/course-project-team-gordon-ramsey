package business.rules.base.response;

import entities.Recipe;

/**
 * Class representing the response of a recipe list from the UseCases
 */
public class UseCaseRecipeListResponse extends UseCaseResponse{
    public Recipe[] recipes;

    /**
     * @param rCode return code of success/failure
     * @param aCode action code
     * @param recipes list of recipes
     */
    public UseCaseRecipeListResponse(RETURN_CODE rCode, ACTION_CODE aCode, Recipe[] recipes){
        super(rCode, aCode);
        this.recipes = recipes;
    }
}
