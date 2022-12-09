package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseAddFavoriteRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;

public class AddToFavoritesUseCase implements UseCase{
    private UseCaseAddFavoriteRequest ucafr;

    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseAddFavoriteRequest){
            this.ucafr = (UseCaseAddFavoriteRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        if(this.ucafr.user.getJournal().addFavourite(this.ucafr.recipe)) return new UseCaseStringResponse(
                                                            UseCaseResponse.RETURN_CODE.SUCCESS,
                                                            UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                                       "Recipe added to user's favorites successfully.");
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "recipe already in favorites.");
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "adding recipe to favorites";
    }
}