package business.rules.usecases;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;

public class UserLogoutUseCase implements UseCase {

    private String[] logoutSuccess = {"Logged out successfully"};
    private String[] logoutFailure = {"Failed to log user out"};
    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {

        return null;
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
