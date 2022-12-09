package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseLoginRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseLoginResponse;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;

public class UserLoginUseCase implements UseCase {
    private UseCaseLoginRequest uclr;

    /**
     * @param ucr a UseCaseRequest, processed when a UseCaseLoginRequest.
     * @return returns a UseCaseResponse with the success/failure of each stage.
     */
    @Override
    public UseCaseResponse process(UseCaseRequest ucr){
        if(ucr instanceof UseCaseLoginRequest){
            this.uclr = (UseCaseLoginRequest) ucr;
        }
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                                              "request data could not be parsed.");
        if(this.uclr.userDB.validateCredentials(this.uclr.username, this.uclr.password))return new UseCaseLoginResponse(
            UseCaseResponse.RETURN_CODE.SUCCESS, UseCaseResponse.ACTION_CODE.LOGIN_USER, this.uclr.userDB.getUser(this.uclr.username));
        else return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                                              UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "credentials invalid.");
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
        return "logging in";
    }
}