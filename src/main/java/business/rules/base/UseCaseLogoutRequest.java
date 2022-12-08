package business.rules.base;

/**
 * Manages request to logout user
 */
public class UseCaseLogoutRequest extends UseCaseRequest{

    private boolean confirmation;

    /**
     * Constructor with stage, confirmation
     * @param stage useCase request stage
     * @param confirmation boolean representing whether user was successfully logged out or not
     */
    public UseCaseLogoutRequest(int stage, boolean confirmation) {
        super(stage);
        this.confirmation = confirmation;
    }

    public boolean getConfirmation() {
        return confirmation;
    }
}
