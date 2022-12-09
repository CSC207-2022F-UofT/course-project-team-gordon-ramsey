package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.request.UseCaseSearchRecipeRequest;
import business.rules.base.response.UseCaseRecipeListResponse;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;
import entities.*;

/**
 * A UseCase that handles the work of searching for a Recipe within the database
 */
public class SearchRecipeUsecase implements UseCase{
    private UseCaseSearchRecipeRequest uckr;
    private Recipe[] recipes;

    /**
     * @param ucr UseCaseRequest storing the active DB, search string, and verbose boolean
     * @return a UseCaseResponse with success enum and string if results retrieved successfully, and failure
     *         enum and string if not.
     */
    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseSearchRecipeRequest){
            this.uckr = (UseCaseSearchRecipeRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        this.recipes = this.uckr.rdb.getRecipes(this.uckr.str, 0, 200, this.uckr.verbose);
        if(this.recipes == null) return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                                                  UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                                                  "request could not be executed.");
        else return new UseCaseRecipeListResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                                  UseCaseResponse.ACTION_CODE.SHOW_DATA_RECIPE, this.recipes);
    }
    /**
     * Get the end stage of this UseCase. Default is 1 for a single step UseCase.
     * @return Integer representing final stage of this UseCase
     */
    public int getEndStage(){
        return 1;
    }
    /**
     * Gets a string describing of the job being performed by this UseCase.
     * @return String describing job of UseCase.
     */
    public String getJob(){
        return "searching recipe";
    }
}