package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseAddGroceryRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;

public class AddToGroceriesUseCase implements UseCase{
    private UseCaseAddGroceryRequest ucagr;

    /**
     * @param ucr UseCaseRequest storing User and Recipe to add to grocery list
     * @return a UseCaseResponse with success enum and string if added successfully, and failure enum and string if not.
     */
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
        return "adding recipe ingredients to grocery list";
    }
}