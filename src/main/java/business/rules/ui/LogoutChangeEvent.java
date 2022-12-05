package business.rules.ui;

import business.rules.UseCaseHandler;

public class LogoutChangeEvent extends ChangeEvent {
    public boolean confirmation;
    public LogoutChangeEvent(boolean confirmation) {
        super(UseCaseHandler.USE_CASE.USER_LOGOUT_USECASE);
        this.confirmation = confirmation;
    }
}
