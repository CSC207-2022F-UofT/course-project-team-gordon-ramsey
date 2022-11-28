package business.rules.usecases;

import business.rules.base.UseCaseAddGroceryRequest;
import business.rules.base.UseCaseRequest;
import business.rules.base.UseCaseResponse;
import business.rules.base.UseCaseStringResponse;
import business.rules.dbs.UserDB;
import entities.Ingredient;
import entities.Recipe;

import java.util.ArrayList;

public class AddToGroceriesUseCase {

    private UseCaseAddGroceryRequest ucrlr;

    private UserDB udb;

    public UseCaseResponse process(UseCaseRequest ucr) {
        if (ucr instanceof UseCaseAddGroceryRequest) {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (Recipe recipe : ((UseCaseAddGroceryRequest) ucr).recipes){

            }
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.SUCCESS, UseCaseResponse.ACTION_CODE.DO_NOTHING); // FIXME
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request data could not be parsed.");
    }

    private void addIfNotExist(Ingredient newItem, ArrayList<Ingredient> list){
        boolean exist = false;
        for (Ingredient item : list){
            if (newItem.equals(item)){
                exist = true;
                break;
            }
        }
        if (!exist) {
            list.add(newItem);
        }
    }




}
