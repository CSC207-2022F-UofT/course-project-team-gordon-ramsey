package business.rules.usecases;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;
import business.rules.dbs.RecipeDB;


public class SearchRecipeUsecase implements UseCase{
    private final String[] searchSuccess = {"Search was a success!"};
    private final String[] searchFailure = {"Search failed!"};
    @Override
    public UseCaseResponse process(UseCaseRequest ucr){
        String keyword = (String) ucr.data[0];
        RecipeDB recipeDB = (RecipeDB) ucr.data[1];
        boolean recipeResponse = recipeDB.validateSearchTerm(keyword);
        if(recipeResponse)
        {
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, searchSuccess);
        }
        else
        {
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, searchFailure);
        }
    }

    public int getEndStage(){
        return 1;
    }
    
    public String getJob(){
        return "searching recipe";
    }
}
