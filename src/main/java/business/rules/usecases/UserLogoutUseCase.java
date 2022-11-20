package business.rules.usecases;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;

public class UserLogoutUseCase implements UseCase {

    private String[] logoutSuccess = {"Logged out successfully"};
    private String[] logoutFailure = {"Failed to log user out"};
    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {
        boolean logoutResult = (boolean) ucr.data[0];
        if (logoutResult){
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    logoutSuccess);
        }
        else {
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, logoutFailure);
        }
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "logging out";
    }
}
