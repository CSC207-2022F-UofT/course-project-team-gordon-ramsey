package business.rules.usecases;

import business.rules.base.*;
import business.rules.dbs.UserDB;

public class UserLoginUseCase implements UseCase {

    private final String loginSuccess= "Logged in user successfully";
    private final String usernameFailure = "User not found";
    private final String passwordFailure = "Incorrect password";

    @Override
    public UseCaseResponse process(UseCaseRequest ucrParameter) {
        UseCaseLoginRequest ucr = (UseCaseLoginRequest) ucrParameter;
        String username = ucr.getUsername();
        String password = ucr.getPassword();
        UserDB userDB = ucr.getUserDB();

        boolean userResponse = userDB.validateUser(username);
        if (userResponse){
            boolean passwordResponse = userDB.validatePassword(username, password);
            if (passwordResponse){
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                        loginSuccess);
            }
            else {
                return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                        passwordFailure);
            }
        }
        else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    usernameFailure);
        }
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "logging in";
    }
}
