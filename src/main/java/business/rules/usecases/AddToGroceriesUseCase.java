package business.rules.usecases;

import business.rules.base.*;
import entities.Ingredient;
import entities.Recipe;


public class AddToGroceriesUseCase implements UseCase {

    private UseCaseAddGroceryRequest ucrlr;

    private final String addGrocerySuccess = "Successfully added to the grocery list";
    private final String addGroceryFailure = "Failed to add to the grocery list";

    public UseCaseResponse process(UseCaseRequest ucr) {
        if (ucr instanceof UseCaseAddGroceryRequest) {
            this.ucrlr = (UseCaseAddGroceryRequest) ucr;
            for (Recipe recipe : this.ucrlr.recipes){
                for (Ingredient ingredient: recipe.getIngredients()){
                    this.ucrlr.user.addToGroceryList(ingredient);
                }
            }
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addGrocerySuccess);
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addGroceryFailure);
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "adding ingredients to grocery list";
    }


}
