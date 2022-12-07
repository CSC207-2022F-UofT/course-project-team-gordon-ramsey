package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseAddGroceryRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;

public class AddToGroceriesUseCase implements UseCase{
    private UseCaseAddGroceryRequest ucagr;

    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseAddGroceryRequest){
            this.ucagr = (UseCaseAddGroceryRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        this.ucagr.user.addToGroceryList(this.ucagr.recipe);
        return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                         UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                         "Recipe added to user's grocery list successfully.");
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "adding recipe ingredients to grocery list";
    }
}