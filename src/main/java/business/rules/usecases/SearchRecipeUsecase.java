package business.rules.usecases;

import business.rules.base.*;
import business.rules.dbs.RecipeDB;
import entities.*;
/**
 * A UseCase that handles the work of searching for a Recipe within the database
 */
public class SearchRecipeUsecase implements UseCase{

    /** a UseCaseRequest whose attributes contain the info needed for process
     */
    private UseCaseKeywordRequest uckr;
    /** Active RecipeDB
     */
    private RecipeDB rdb;
    /** Array of recipes returned from search
     */
    private Recipe[] recipes;
    /**
     *
     * @param ucr UseCaseRequest storing the active DB, search string, and verbose boolean
     * @return a UseCaseResponse with success enum and string if results retrieved successfully, and failure
     *         enum and string if not.
     */
    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseKeywordRequest){
            this.uckr = (UseCaseKeywordRequest) ucr;
            if(this.uckr.db instanceof RecipeDB){
                this.rdb = (RecipeDB) this.uckr.db;
            }
            else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request database could not be parsed.");
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request data could not be parsed.");
        this.recipes = this.rdb.getRecipes(this.uckr.str, 0, 20, this.uckr.verbose);
        if(this.recipes == null) return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "request could not be executed.");
        else return new UseCaseRecipeListResponse(UseCaseResponse.RETURN_CODE.SUCCESS, UseCaseResponse.ACTION_CODE.SHOW_DATA_RECIPE, this.recipes);
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