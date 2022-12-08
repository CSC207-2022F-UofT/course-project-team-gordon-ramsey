package business.rules.usecases;

import business.rules.base.*;
import entities.Ingredient;
import entities.Recipe;

/**
 * Adds ingredients of recipe to groceries list
 */
public class AddToGroceriesUseCase implements UseCase {
    private UseCaseAddGroceryRequest ucrlr;

    private final String addGrocerySuccess = "Successfully added to the grocery list";
    private final String addGroceryFailure = "Failed to add to the grocery list";

    /**
     * Checks if the use case request is for AddToGroceriesUseCase,if true adds ingredients from recipe to grocery list
     * @param ucr use case request from user
     * @return UseCaseResponse of success or failure
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
     * Override for getEndStage
     * @return int representing the final stage of UseCase
     */
    @Override
    public int getEndStage() {
        return 1;
    }

    /**
     * Override for getJob
     * @return String representing UseCase action
     */
    @Override
    public String getJob() {
        return "adding ingredients to grocery list";
    }


}
