package business.rules.usecases;


import business.rules.base.*;

public class UserLogoutUseCase implements UseCase {

    private final String logoutSuccess = "Logged out successfully";
    private final String logoutFailure = "Failed to log user out";
    @Override
    public UseCaseResponse process(UseCaseRequest ucrParameter) {
        UseCaseLogoutRequest ucr = (UseCaseLogoutRequest) ucrParameter;
        boolean logoutResult = ucr.getConfirmation();
        if (logoutResult){
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    logoutSuccess);
        }
        else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
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
