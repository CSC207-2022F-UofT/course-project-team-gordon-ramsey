package business.rules;

import business.rules.base.*;
import business.rules.base.UseCaseResponse.ACTION_CODE;
import business.rules.ui.ChangeEvent;
import business.rules.ui.RecipeSearchChangeEvent;
import business.rules.usecases.*;

public class UseCaseHandler{
    public static enum USE_CASE{
        ADD_RECIPE_USECASE,
        REMIX_RECIPE_USECASE,
        SEARCH_RECIPE_USECASE,
        SELECT_RECIPE_USECASE,
        USER_LOGIN_USECASE,
        USER_LOGOUT_USECASE,
        CREATE_USER_USECASE
    }

    private UseCaseResponse ucrp;
    private UseCaseRequest ucrq;
    private UseCase uc;
    private Presenter presenter;

    public UseCaseHandler(Presenter presenter){
        this.presenter = presenter;
        if(presenter == null) throw new IllegalArgumentException("UseCaseHandler initialized with null parameter.");
    }

    /**
     * maps use case requests to appropriate classes, handles multistage interactions.
     * 
     * Response Types:
     * 
     * UseCaseResponse.RETURN_CODE.FAILURE : UseCaseStringResponse
     * ACTION_CODE.SHOW_DATA_STRING : UseCaseStringResponse
     * ACTION_CODE.SHOW_DATA_RECIPE : UseCaseRecipeListResponse
     * 
     */
    public void handle(ChangeEvent e){
        if(e.use_case_id == USE_CASE.SEARCH_RECIPE_USECASE){
            RecipeSearchChangeEvent rsce = (RecipeSearchChangeEvent) e;
            this.uc = new SearchRecipeUsecase();
            this.ucrq = new UseCaseKeywordRequest(rsce.keyword, this.presenter.getRecipeDB(), rsce.verbose, 1);
        }
        else return;
        while(this.ucrq.stage <= this.uc.getEndStage()){
            this.ucrp = this.uc.process(this.ucrq);
            if(this.ucrp.rCode == UseCaseResponse.RETURN_CODE.FAILURE){
                this.presenter.showUser(uc.getJob() + " failed : " + ((UseCaseStringResponse)this.ucrp).str);
                break;
            }
            this.ucrq.stage += 1;
            if(this.ucrp.aCode == ACTION_CODE.DO_NOTHING) continue;
            else if(this.ucrp.aCode == ACTION_CODE.SHOW_DATA_STRING){
                this.presenter.showUser(((UseCaseStringResponse)this.ucrp).str);
            }
            else if(this.ucrp.aCode == ACTION_CODE.SHOW_DATA_RECIPE){
                this.presenter.showUser(((UseCaseRecipeListResponse)this.ucrp).recipes);
            }
        }
    }
}