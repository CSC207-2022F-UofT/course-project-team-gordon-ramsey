package business.rules.usecases;

import business.rules.base.UseCase;
import business.rules.base.request.UseCaseClearGroceriesRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;

public class ClearGroceriesUseCase implements UseCase{
    private UseCaseClearGroceriesRequest uccgr;

    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseClearGroceriesRequest){
            this.uccgr = (UseCaseClearGroceriesRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        this.uccgr.user.clearGroceryList();
        return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                         UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                         "User's grocery list cleared successfully.");
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "clearing user's grocery list";
    }
}