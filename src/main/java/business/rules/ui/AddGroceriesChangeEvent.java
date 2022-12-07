package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;

public class AddGroceriesChangeEvent extends ChangeEvent{
    public Recipe recipe;
    public AddGroceriesChangeEvent(Recipe recipe) {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
        this.recipe = recipe;
    }
}
