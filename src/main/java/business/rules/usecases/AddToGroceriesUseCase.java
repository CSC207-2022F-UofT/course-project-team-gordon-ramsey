package business.rules.usecases;

import business.rules.base.*;
import entities.Ingredient;
import entities.Recipe;

/**
 * A UseCase that handles the work of adding all Ingredients in a Recipe to a User's Grocery List
 */
public class AddToGroceriesUseCase implements UseCase {

    /** a UseCaseRequest whose attributes contain the info needed for process
     */
    private UseCaseAddGroceryRequest ucrlr;

    /** Success and failure messages for result of process
     */
    private final String addGrocerySuccess = "Successfully added to the grocery list";
    private final String addGroceryFailure = "Failed to add to the grocery list";

    /**
     *
     * @param ucr UseCaseRequest storing User and Recipe to add to grocery list
     * @return a UseCaseResponse with success enum and string if added successfully, and failure enum and string if not.
     */
    public UseCaseResponse process(UseCaseRequest ucr) {
        if (ucr instanceof UseCaseAddGroceryRequest) {
            this.ucrlr = (UseCaseAddGroceryRequest) ucr;
            for (Ingredient ingredient : ucrlr.recipe.getIngredients()) {
                this.ucrlr.user.addToGroceryList(ingredient);
            }
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addGrocerySuccess);
        } else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addGroceryFailure);
        }
    }

    /**
     * Get the end stage of this UseCase. Default is 1 for a single step UseCase.
     * @return Integer representing final stage of this UseCase
     */
    @Override
    public int getEndStage() {
        return 1;
    }

    /**
     * Gets a string describing of the job being performed by this UseCase.
     * @return String describing job of UseCase.
     */
    @Override
    public String getJob() {
        return "adding ingredients to grocery list";
    }


}
