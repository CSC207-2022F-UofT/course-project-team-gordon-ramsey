package business.rules;

import business.rules.base.*;
import business.rules.base.UseCaseResponse.ACTION_CODE;
import business.rules.usecases.*;
import entities.Recipe;

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
    public void handle(USE_CASE uc_id, Object[] data){
        if(uc_id == USE_CASE.SEARCH_RECIPE_USECASE){
            this.uc = new SearchRecipeUsecase();
            this.ucrq = new UseCaseKeywordRequest((String)data[0], this.presenter.getRecipeDB(), (boolean)data[1], 1);
        }
        if(uc_id == USE_CASE.SELECT_RECIPE_USECASE){
            this.uc = new SelectRecipeUseCase();
            this.ucrq = new UseCaseSelectRequest(1, this.presenter.getUser(), (Recipe)data[0], (boolean)data[1]);
            int i;
            for (i = 0; i<3; i++) {
                UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
                if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                    System.out.print(resp.getStr());
                    return;
                }
            }
        }
        if(uc_id == USE_CASE.REMIX_RECIPE_USECASE){
            this.uc = new RemixRecipeUseCase();
            this.ucrq = new UseCaseRemixRequest(1, (Object[][])data[0], (String)data[1], (String)data[2],
                    (String[][])data[3], (String)data[4], (String)data[5], (String)data[6],
                    this.presenter.getRecipeDB());
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
        }
        if(uc_id == USE_CASE.CREATE_USER_USECASE){
            this.uc = new UserRegisterUseCase();
            this.ucrq = new UseCaseRegisterRequest(1, (String)data[0], (String)data[1],
                    (String)data[2], this.presenter.getUserDB());
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
        }
        if(uc_id == USE_CASE.USER_LOGIN_USECASE){
            this.uc = new UserLoginUseCase();
            this.ucrq = new UseCaseLoginRequest(1, (String)data[0], (String)data[1], this.presenter.getUserDB());
            UseCaseStringResponse resp = (UseCaseStringResponse) uc.process(ucrq);
            if (resp.rCode == UseCaseResponse.RETURN_CODE.FAILURE) {
                System.out.print(resp.getStr());
                return;
            }
        }
        if(uc_id == USE_CASE.USER_LOGIN_USECASE){
            this.uc = new UserLogoutUseCase();
            this.ucrq = new UseCaseLogoutRequest(1, (boolean)data[0]);
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
                for(Recipe i : ((UseCaseRecipeListResponse)this.ucrp).recipes){
                    this.presenter.showUser(i);
                }
            }
        }
    }
}