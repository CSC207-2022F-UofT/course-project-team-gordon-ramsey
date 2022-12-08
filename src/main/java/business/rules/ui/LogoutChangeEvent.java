package business.rules.ui;

import business.rules.UseCaseHandler;
/**
 * A Class that changes the state of the presenter when the user logs out of their account.
 *
 */
public class LogoutChangeEvent extends ChangeEvent {
    /**
     * boolean representing successful/unsuccessful logout of the user
     */
    public boolean confirmation;

    /**
     *
     * @param confirmation represents confirmation or denial of an attempt to logout by the user
     */
    public LogoutChangeEvent(boolean confirmation) {
        super(UseCaseHandler.USE_CASE.USER_LOGOUT_USECASE);
        this.confirmation = confirmation;
    }
}
