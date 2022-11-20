package business.rules;

import business.rules.usecases.*;

public class UseCaseHandler{
    public static enum USE_CASE{
        ADD_RECIPE_USECASE,
        REMIX_RECIPE_USECASE,
        SEARCH_RECIPE_USECASE,
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

    public void handle(USE_CASE uc_id, Object[] data){
        if(uc_id == USE_CASE.SEARCH_RECIPE_USECASE){
            this.uc = new SearchRecipeUsecase();
        }
        else if(uc_id == USE_CASE.REMIX_RECIPE_USECASE){
            this.uc = new RemixRecipeUseCase();
        }
        this.ucrq = new UseCaseRequest(data, 1);
        while(this.ucrq.stage <= this.uc.getEndStage()){
            this.ucrp = this.uc.process(this.ucrq);
            // handle here
            this.ucrq.stage += 1;
        }
    }
}
