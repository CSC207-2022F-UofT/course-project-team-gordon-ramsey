package business.rules;

import business.rules.base.*;
import business.rules.base.UseCaseResponse.ACTION_CODE;
import business.rules.ui.*;
import business.rules.usecases.*;

public class UseCaseHandler{
    public static enum USE_CASE{
        ADD_RECIPE_USECASE,
        REMIX_RECIPE_USECASE,
        SEARCH_RECIPE_USECASE,
        SELECT_RECIPE_USECASE,
        USER_LOGIN_USECASE,
        USER_LOGOUT_USECASE,
        CREATE_USER_USECASE,
        ADD_TO_GROCERIES_USECASE
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
        UseCaseHandler.USE_CASE uc_id = e.use_case_id;
        if(uc_id == USE_CASE.SEARCH_RECIPE_USECASE){
            RecipeSearchChangeEvent rsce = (RecipeSearchChangeEvent) e;
            this.uc = new SearchRecipeUsecase();
            this.ucrq = new UseCaseKeywordRequest(rsce.keyword, this.presenter.getRecipeDB(), rsce.verbose, 1);
        }
        else if(uc_id == USE_CASE.SELECT_RECIPE_USECASE){
            SelectChangeEvent se = (SelectChangeEvent) e;
            this.uc = new SelectRecipeUseCase();
            this.ucrq = new UseCaseSelectRequest(1, this.presenter.getUser(), presenter.getSelectedRecipe(),
                    se.favourite);
            int i;
            for (i = 0; i<3; i++) {
                UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
                if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                    System.out.print(resp.getStr());
                    return;
                }
            }
        }
        else if(uc_id == USE_CASE.REMIX_RECIPE_USECASE){
            RemixChangeEvent re = (RemixChangeEvent) e;
            this.uc = new RemixRecipeUseCase();
            this.ucrq = new UseCaseRemixRequest(1, re.toRemix, re.newName, re.newDescription,
                    re.newIngredients, re.newInstructions, re.newCookTime, re.newYield,
                    this.presenter.getRecipeDB());
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
        }
        else if(uc_id == USE_CASE.CREATE_USER_USECASE){
            CreateUserChangeEvent ce = (CreateUserChangeEvent) e;
            this.uc = new UserRegisterUseCase();
            this.ucrq = new UseCaseRegisterRequest(1, ce.username, ce.password,
                    ce.name, this.presenter.getUserDB());
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
        }
        else if(uc_id == USE_CASE.USER_LOGIN_USECASE){
            LoginUserChangeEvent le = (LoginUserChangeEvent) e;
            this.uc = new UserLoginUseCase();
            this.ucrq = new UseCaseLoginRequest(1, le.username, le.password, this.presenter.getUserDB());
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
        }
        else if(uc_id == USE_CASE.USER_LOGOUT_USECASE){
            LogoutChangeEvent le = (LogoutChangeEvent) e;
            this.uc = new UserLogoutUseCase();
            this.ucrq = new UseCaseLogoutRequest(1, le.confirmation);
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
        }
        else if(uc_id == USE_CASE.ADD_RECIPE_USECASE){
            AddNewRecipeChangeEvent ae = (AddNewRecipeChangeEvent) e;
            this.uc = new AddNewRecipeUseCase();
            this.ucrq = new UseCaseAddNewRecipeRequest(1, presenter.getRecipeDB(), presenter.getUser(),
                    ae.newName, ae.newDescription, ae.newIngredients, ae.newInstructions, ae.newCookTime, ae.newYield);
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
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