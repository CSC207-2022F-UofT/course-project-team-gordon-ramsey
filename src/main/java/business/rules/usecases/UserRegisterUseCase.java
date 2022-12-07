package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseRegisterRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;
import entities.User;

/**
 * A UseCase handling the registration of a new user and addition to the UserDB
 */
public class UserRegisterUseCase implements UseCase{
    private UseCaseRegisterRequest ucrr;

    /**
     * @param ucrParameter A UseCaseRequest containing the desired username, password, and name for the new user
     * @return Returns a UseCaseResponse with the success/failure of each stage
     */
    @Override
    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseRegisterRequest){
            this.ucrr = (UseCaseRegisterRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        if(this.ucrr.udb.addUser(new User(this.ucrr.fullname, this.ucrr.username, this.ucrr.password)))
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                                             UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                             "registered user successfully.");
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "user could not be added to the local database.");
    }

    /**
     * @return Returns an int representing the final stage of this UseCase
     */
    @Override
    public int getEndStage() {
        return 1;
    }

    /**
     * @return Returns a string representing the work being done by this UseCase
     */
    @Override
    public String getJob() {
        return "registering user";
    }
}
