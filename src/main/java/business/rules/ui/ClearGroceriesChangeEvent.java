package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.User;

public class ClearGroceriesChangeEvent extends ChangeEvent{
    public User user;

    /*
     * utiltiy no argument constructor so that UI can initiate change event.
     */
    public ClearGroceriesChangeEvent() {
        super(UseCaseHandler.USE_CASE.CLEAR_GROCERIES_USECASE);
        this.user = null;
    }

    public ClearGroceriesChangeEvent(User user) {
        super(UseCaseHandler.USE_CASE.CLEAR_GROCERIES_USECASE);
        this.user = user;
    }
}