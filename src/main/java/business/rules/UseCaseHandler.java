package business.rules;

import business.rules.base.*;
import business.rules.base.request.*;
import business.rules.base.response.*;
import business.rules.base.response.UseCaseResponse.ACTION_CODE;
import business.rules.ui.*;
import business.rules.usecases.*;

public class UseCaseHandler{
    public static enum USE_CASE{
        ADD_RECIPE_USECASE,
        REMIX_RECIPE_USECASE,
        SEARCH_RECIPE_USECASE,
        SELECT_RECIPE_USECASE,
        USER_LOGIN_USECASE,
        CREATE_USER_USECASE,
        ADD_TO_GROCERIES_USECASE,
        ADD_TO_FAVORITES_USECASE
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
            this.ucrq = new UseCaseSearchRecipeRequest(rsce.keyword, this.presenter.getRecipeDB(), rsce.verbose, 1);
        }
        /*else if(uc_id == USE_CASE.REMIX_RECIPE_USECASE){
            RemixChangeEvent re = (RemixChangeEvent) e;
            this.uc = new RemixRecipeUseCase();
            this.ucrq = new UseCaseRemixRequest(1, re.toRemix, re.newName, re.newDescription,
                    re.newIngredients, re.newInstructions, re.newCookTime, re.newYield,
                    this.presenter.getRecipeDB());
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.str);
                return;
            }
        }*/
        else if(uc_id == USE_CASE.CREATE_USER_USECASE){
            CreateUserChangeEvent cuce = (CreateUserChangeEvent) e;
            this.uc = new UserRegisterUseCase();
            this.ucrq = new UseCaseRegisterRequest(cuce.username, cuce.password,
                                                   cuce.fullname, this.presenter.getUserDB(), 1);
        }
        else if(uc_id == USE_CASE.USER_LOGIN_USECASE){
            LoginUserChangeEvent luce = (LoginUserChangeEvent) e;
            this.uc = new UserLoginUseCase();
            this.ucrq = new UseCaseLoginRequest(luce.username, luce.password,
                                                this.presenter.getUserDB(), 1);
        }
        else if(uc_id == USE_CASE.ADD_TO_FAVORITES_USECASE){
            AddToFavoritesChangeEvent afce = (AddToFavoritesChangeEvent) e;
            this.uc = new AddToFavoritesUseCase();
            this.ucrq = new UseCaseAddFavoriteRequest(afce.recipe, afce.user, 1);
        }
        /*else if(uc_id == USE_CASE.ADD_RECIPE_USECASE){
            AddNewRecipeChangeEvent ae = (AddNewRecipeChangeEvent) e;
            this.uc = new AddNewRecipeUseCase();
            this.ucrq = new UseCaseAddNewRecipeRequest(1, presenter.getRecipeDB(), presenter.getUser(),
                    ae.newName, ae.newDescription, ae.newIngredients, ae.newInstructions, ae.newCookTime, ae.newYield);
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.str);
                return;
            }
        }*/
        else if (uc_id == USE_CASE.ADD_TO_GROCERIES_USECASE){
            AddGroceriesChangeEvent agce = (AddGroceriesChangeEvent) e;
            this.uc = new AddToGroceriesUseCase();
            this.ucrq = new UseCaseAddGroceryRequest(agce.recipe, agce.user, 1);
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
            else if(this.ucrp.aCode == ACTION_CODE.LOGIN_USER){
                this.presenter.setUser(((UseCaseLoginResponse)this.ucrp).user);
                this.presenter.showUser("User logged in successfully.");
                this.presenter.showUser("Welcome back " + this.presenter.getUser().getName() + "!");
            }
        }
    }
}