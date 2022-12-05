package business.rules.usecases;


import business.rules.base.*;


/**
 * A UseCase that handles logging the active user out
 */
public class UserLogoutUseCase implements UseCase {

    private final String logoutSuccess = "Logged out successfully";
    private final String logoutFailure = "Failed to log user out";

    /**
     *
     * @param ucrParameter a UseCaseRequest containing a boolean indicating login/logout success
     * @return
     */
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
        return "logging out";
    }
}
