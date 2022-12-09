package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseSaveRecipeRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;

public class SaveRecipeUseCase implements UseCase{
    private UseCaseSaveRecipeRequest ucsrn;

    public UseCaseResponse process(UseCaseRequest ucr){
        if (ucr instanceof UseCaseSaveRecipeRequest){
            this.ucsrn = (UseCaseSaveRecipeRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, 
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        if(this.ucsrn.rdb.addLocalRecipe(this.ucsrn.recipe)) return new UseCaseStringResponse(
                                                UseCaseResponse.RETURN_CODE.SUCCESS,
                                                UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                                "Recipe saved locally successfully.");
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "could not add recipe to the local database.");
    }

    @Override
    public int getEndStage(){
        return 1;
    }

    @Override
    public String getJob(){
        return "saving recipe locally";
    }
}