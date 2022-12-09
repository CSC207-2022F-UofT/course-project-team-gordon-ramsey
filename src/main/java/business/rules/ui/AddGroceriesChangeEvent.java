package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;
import entities.User;

public class AddGroceriesChangeEvent extends ChangeEvent{
    public Recipe recipe;
    public User user;

    public AddGroceriesChangeEvent() {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
        this.recipe = null;
        this.user = null;
    }

    public AddGroceriesChangeEvent(Recipe recipe, User user) {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
        this.recipe = recipe;
        this.user = user;
    }
}