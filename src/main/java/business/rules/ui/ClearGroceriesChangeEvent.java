package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.User;

public class ClearGroceriesChangeEvent extends ChangeEvent{
    public User user;

    public ClearGroceriesChangeEvent() {
        super(UseCaseHandler.USE_CASE.CLEAR_GROCERIES_USECASE);
        this.user = null;
    }

    public ClearGroceriesChangeEvent(User user) {
        super(UseCaseHandler.USE_CASE.CLEAR_GROCERIES_USECASE);
        this.user = user;
    }
}