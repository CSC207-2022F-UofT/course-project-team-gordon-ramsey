package business.rules.usecases;

import business.rules.base.*;
import business.rules.dbs.RecipeDB;
import entities.*;

public class SearchRecipeUsecase implements UseCase{
    private UseCaseKeywordRequest uckr;
    private RecipeDB rdb;
    private Recipe[] recipes;
    
    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseKeywordRequest){
            this.uckr = (UseCaseKeywordRequest) ucr;
            if(this.uckr.db instanceof RecipeDB){
                this.rdb = (RecipeDB) this.uckr.db;
            }
            else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request database could not be parsed.");
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request data could not be parsed.");
        this.recipes = this.rdb.getRecipes(this.uckr.str, 0, 20);
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