package business.rules.usecases;

import business.rules.base.*;
import entities.Recipe;
import entities.RecipeEntry;
import entities.User;

import java.time.Instant;

/**
 * A UseCase that handles selecting a Recipe based on input from the end user
 */
public class SelectRecipeUseCase implements UseCase {

    /** Success and failure messages for result at each stage of process
     */
    private final String selectSuccess = "Recipe selected successfully";
    private final String favouriteSuccess = "Recipe added to favourites successfully";
    private final String journalSuccess = "Recipe added to journal successfully";
    private final String selectFailure = "Failed to select recipe";
    private final String favouriteFailure = "Failed to favourite recipe";
    private final String journalFailure = "Failed to add to journal";

    /**
     *
     * @param ucr a UseCaseRequest with the active User and Recipe being selected
     * @return Returns a UseCaseResponse with the success/failure of each stage
     */
    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {
        UseCaseSelectRequest ucrs = (UseCaseSelectRequest) ucr;
        User user = ucrs.getUser();
        Recipe recipe = ucrs.getRecipe();
        if (ucr.stage == 1){
            if (recipe != null){
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, selectSuccess);
            }
            else {
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, selectFailure);
            }
        }
        if (ucr.stage == 2){
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
        if (ucr.stage == 3){
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

    /**
     *
     * @return Returns an int representing the final stage of this UseCase
     */
    @Override
    public int getEndStage() {
        return 3;
    }

    /**
     *
     * @return Returns a string representing the work being done by this UseCase
     */
    @Override
    public String getJob() {
        return "selecting recipe";
    }
}
