package business.rules.usecases;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;
import business.rules.dbs.UserDB;
import entities.User;

public class UserRegisterUseCase implements UseCase {

    private final Object[] success = {"User registered successfully"};
    private final Object[] failure = {"Failed to register user"};

    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {
        // Get user information from UseCaseRequest - collection handled in CLI
        String username = (String) ucr.data[0];
        String password = (String) ucr.data[1];
        String fullname = (String) ucr.data[2];
        UserDB userDB = (UserDB) ucr.data[3];

        // Create new User object to add to UserDB
        User newUser = new User(fullname, username, password);
        boolean response = userDB.addUser(newUser);

        if (response){
            //return UseCaseResponse
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.SUCCESS, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    this.success);
        }
        else {
            //return UseCaseResponse fail
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
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
