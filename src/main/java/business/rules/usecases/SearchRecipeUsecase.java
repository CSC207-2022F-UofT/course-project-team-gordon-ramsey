package business.rules.usecases;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;

public class SearchRecipeUsecase implements UseCase{
    
    public UseCaseResponse process(UseCaseRequest ucr){
        // initiate work here
        return null;
    }

    public int getEndStage(){
        return 1;
    }
    
    public String getJob(){
        return "searching recipe";
    }
}
