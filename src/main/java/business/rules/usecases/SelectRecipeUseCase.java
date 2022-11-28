package business.rules.usecases;

import business.rules.base.*;
import entities.Recipe;
import entities.User;

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
            user.getJournal().
        }
        if (ucr.stage == 2){

        }
        return null;
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "selecting recipe";
    }
}
