package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.request.UseCaseSearchRecipeRequest;
import business.rules.base.response.UseCaseRecipeListResponse;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;
import entities.*;

public class SearchRecipeUsecase implements UseCase{
    private UseCaseSearchRecipeRequest uckr;
    private Recipe[] recipes;
    
    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseSearchRecipeRequest){
            this.uckr = (UseCaseSearchRecipeRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request data could not be parsed.");
        this.recipes = this.uckr.rdb.getRecipes(this.uckr.str, 0, 200, this.uckr.verbose);
        if(this.recipes == null) return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request could not be executed.");
        else return new UseCaseRecipeListResponse(UseCaseResponse.RETURN_CODE.SUCCESS, UseCaseResponse.ACTION_CODE.SHOW_DATA_RECIPE, this.recipes);
    }

    public int getEndStage(){
        return 1;
    }
    
    public String getJob(){
        return "searching recipe";
    }
}