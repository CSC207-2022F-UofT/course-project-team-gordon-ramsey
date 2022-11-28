package business.rules.usecases;

import business.rules.base.UseCaseAddGroceryRequest;
import business.rules.base.UseCaseRequest;
import business.rules.base.UseCaseResponse;
import business.rules.base.UseCaseStringResponse;
import business.rules.dbs.UserDB;
import entities.GroceryList;
import entities.Ingredient;
import entities.Recipe;
import entities.User;

import java.util.ArrayList;

public class AddToGroceriesUseCase {

    private UseCaseAddGroceryRequest ucrlr;

    private final String addGrocerySuccess = "Successfully added to the grocery list";
    private final String addGroceryFailure = "Failed to add to the grocery list";

    public UseCaseResponse process(UseCaseRequest ucr) {
        if (ucr instanceof UseCaseAddGroceryRequest) {
            this.ucrlr = (UseCaseAddGroceryRequest) ucr;
            ArrayList<Ingredient> newIngredients = new ArrayList<>();
            for (Recipe recipe : this.ucrlr.recipes){
                for (Ingredient ingredient: recipe.getIngredients()){
                    this.ucrlr.user.addToGroceryList(ingredient);
                }
            }
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addGrocerySuccess);
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addGroceryFailure);
    }





}
