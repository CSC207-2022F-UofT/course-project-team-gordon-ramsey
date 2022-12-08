package business.rules.usecases;

import business.rules.base.*;
import business.rules.dbs.UserDB;
/**
 * A UseCase of verifying and logging in a User
 */
public class UserLoginUseCase implements UseCase {

    /** Success and failure messages for result of process
     */
    private final String loginSuccess= "Logged in user successfully";
    private final String usernameFailure = "User not found";
    private final String passwordFailure = "Incorrect password";

    /**
     *
     * @param ucrParameter a UseCaseRequest containing the username and password of the user logging in with the active
     *                     userDB.
     * @return Returns a UseCase response with the success/failure of each stage
     */
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

    /**
     *
     * @return Returns an int representing the final stage of this UseCase
     */
    @Override
    public int getEndStage() {
        return 1;
    }

    /**
     *
     * @return Returns a string representing the work being done by this UseCase
     */
    @Override
    public String getJob() {
        return "logging in";
    }
}
