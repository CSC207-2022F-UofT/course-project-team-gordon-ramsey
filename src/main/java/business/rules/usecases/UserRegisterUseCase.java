package business.rules.usecases;


import business.rules.base.*;
import business.rules.dbs.UserDB;
import entities.User;

public class UserRegisterUseCase implements UseCase {

    private final String success = "User registered successfully";
    private final String failure = "Failed to register user";

    @Override
    public UseCaseResponse process(UseCaseRequest ucrParameter) {
        UseCaseRegisterRequest ucr = (UseCaseRegisterRequest) ucrParameter;
        // Get user information from UseCaseRequest - collection handled in CLI
        String username = ucr.getUsername();
        String password = ucr.getPassword();
        String fullname = ucr.getFullname();
        UserDB userDB = ucr.getUDB();

        // Create new User object to add to UserDB
        User newUser = new User(fullname, username, password);
        boolean response = userDB.addUser(newUser);

        if (response){
            //return UseCaseResponse
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    this.success);
        }
        else {
            //return UseCaseResponse fail
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    this.failure);
        }
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "registering user";
    }
}
