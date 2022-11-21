package business.rules.base;

public class UseCaseLogoutRequest extends UseCaseRequest{

    private boolean confirmation;
    public UseCaseLogoutRequest(int stage, boolean confirmation) {
        super(stage);
        this.confirmation = confirmation;
    }

    public boolean getConfirmation() {
        return confirmation;
    }
}
