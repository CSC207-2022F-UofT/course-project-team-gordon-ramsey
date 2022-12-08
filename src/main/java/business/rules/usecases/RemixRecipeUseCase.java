package business.rules.usecases;

import business.rules.base.UseCase;
import business.rules.base.request.UseCaseFieldReplyRequest;
import business.rules.base.request.UseCaseRemixRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseFieldQueryResponse;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;
import business.rules.ui.UI.FIELD_TYPE;
import business.rules.ui.UI.MODIFICATION_TYPE;
import entities.Journal;
import entities.Recipe;

/**
 * A UseCase that handles remixing a Recipe and adding it to the RecipeDB based on input from the end user
 */

public class RemixRecipeUseCase implements UseCase{
    private UseCaseFieldReplyRequest ucfrr;
    private String[][] recipe_collection;
    private Journal user_journal;

    /**
     * @param ucr A UseCaseRequest with the required Recipe attributes to complete the remix
     * @return Returns a UseCaseResponse for each stage, accordingly:
     * 
     * stage 1 : usecase begins, request new recipe description.
     * stage 2 : got reply containing new description, request new ingredients info.
     * stage 3 : got new ingredients info, request new cooktime.
     * stage 4 : got new cooktime, request new yield of the recipe.
     * stage 5 : got new yield, add to user's journal, reply success / fail in parsing.
     */
    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseRemixRequest && ucr.stage == 1){
            UseCaseRemixRequest ucrr = (UseCaseRemixRequest) ucr;
            this.recipe_collection = ucrr.original_recipe.getCollection();
            this.user_journal = ucrr.user.getJournal();
            return new UseCaseFieldQueryResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                                 UseCaseResponse.ACTION_CODE.ASK_USER_FIELD,
                                                 this.recipe_collection[Recipe.DESCRIPTION_INDEX],
                                                 MODIFICATION_TYPE.EDIT_VALUES,
                                                 FIELD_TYPE.STRING);
        }
        else if(ucr instanceof UseCaseFieldReplyRequest && ucr.stage > 1 && this.recipe_collection != null){
            this.ucfrr = (UseCaseFieldReplyRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, 
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        switch(this.ucfrr.stage){
            case 2:this.recipe_collection[Recipe.DESCRIPTION_INDEX] = this.ucfrr.field_response;
                   return new UseCaseFieldQueryResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                                        UseCaseResponse.ACTION_CODE.ASK_USER_FIELD,
                                                        this.recipe_collection[Recipe.INGREDIENTS_INDEX],
                                                        MODIFICATION_TYPE.EDIT_AND_ADD_REMOVE_VALUES,
                                                        FIELD_TYPE.ORDERED_INT_WORD_STRING);
            case 3:this.recipe_collection[Recipe.INGREDIENTS_INDEX] = this.ucfrr.field_response;
                   return new UseCaseFieldQueryResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                                        UseCaseResponse.ACTION_CODE.ASK_USER_FIELD,
                                                        this.recipe_collection[Recipe.COOKTIME_INDEX],
                                                        MODIFICATION_TYPE.EDIT_VALUES,
                                                        FIELD_TYPE.INTEGER);
            case 4:this.recipe_collection[Recipe.COOKTIME_INDEX] = this.ucfrr.field_response;
                   return new UseCaseFieldQueryResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                                        UseCaseResponse.ACTION_CODE.ASK_USER_FIELD,
                                                        this.recipe_collection[Recipe.YIELD_INDEX],
                                                        MODIFICATION_TYPE.EDIT_VALUES,
                                                        FIELD_TYPE.FLOAT);
            case 5:this.recipe_collection[Recipe.YIELD_INDEX] = this.ucfrr.field_response;
                   return this.end();
        }
        return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                         UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                         "invalid usecase request stage.");
    }

    public UseCaseResponse end(){
        Recipe remixed_recipe = Recipe.parse(recipe_collection);
        if(remixed_recipe == null) return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                                                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                                                    "failed to parse remixed recipe.");
        this.user_journal.addFavourite(remixed_recipe);
        return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                         UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                         "Successfully remixed recipe and saved to user's favorites.");
    }

    /**
     * @return Returns an int representing the final stage of this UseCase
     */
    public int getEndStage(){
        return 5;
    }

    /**
     * @return Returns a string representing the work being done by this UseCase
     */
    public String getJob(){
        return "remixing and saving recipe";
    }
}