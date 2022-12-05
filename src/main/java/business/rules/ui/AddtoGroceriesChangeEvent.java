package business.rules.ui;

import business.rules.UseCaseHandler;

public class AddtoGroceriesChangeEvent extends ChangeEvent{
    public AddtoGroceriesChangeEvent(UseCaseHandler.USE_CASE use_case) {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
    }
}
