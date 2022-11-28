package business.rules.usecases;

import business.rules.base.*;
import entities.Recipe;
import entities.RecipeEntry;
import entities.User;

import java.time.Instant;

public class SelectRecipeUseCase implements UseCase {

    private final String selectSuccess = "Recipe selected successfully";
    private final String favouriteSuccess = "Recipe added to favourites successfully";
    private final String journalSuccess = "Recipe added to journal successfully";
    private final String selectFailure = "Failed to select recipe";
    private final String favouriteFailure = "Failed to favourite recipe";
    private final String journalFailure = "Failed to add to journal";
    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {
        UseCaseSelectRequest ucrs = (UseCaseSelectRequest) ucr;
        User user = ucrs.getUser();
        Recipe recipe = ucrs.getRecipe();
        if (ucr.stage == 0){
            if (recipe != null){
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, selectSuccess);
            }
            else {
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, selectFailure);
            }
        }
        if (ucr.stage == 1){
            boolean resp = user.getJournal().addEntry(new RecipeEntry(Instant.now(),recipe));
            if (resp){
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, journalSuccess);
            }
            else {
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, journalFailure);
            }
        }
        if (ucr.stage == 2){
            if (ucrs.getFavourite()){
                boolean resp = user.getJournal().addFavourite(recipe);
                if (resp){
                    return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                            UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                            favouriteSuccess);
                }
                else {
                    return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                            UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, favouriteFailure);
                }
            }
        }
        return null;
    }

    @Override
    public int getEndStage() {
        return 2;
    }

    @Override
    public String getJob() {
        return "selecting recipe";
    }
}
