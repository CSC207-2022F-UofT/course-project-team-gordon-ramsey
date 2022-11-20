package business.rules.usecases;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;
import business.rules.dbs.UserDB;

public class UserLoginUseCase implements UseCase {

    private String[] loginSuccess= {"Logged in user successfully"};
    private String[] usernameFailure = {"User not found"};
    private String[] passwordFailure = {"Incorrect password"};

    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {
        String username = (String) ucr.data[0];
        String password = (String) ucr.data[1];
        UserDB userDB = (UserDB) ucr.data[2];

        boolean userResponse = userDB.validateUser(username);
        if (userResponse == true){
            boolean passwordResponse = userDB.validatePassword(username, password);
            if (passwordResponse == true){
                return new UseCaseResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                        loginSuccess);
            }
            else {
                return new UseCaseResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                        UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                        passwordFailure);
            }
        }
        else {
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.FAILURE,
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
