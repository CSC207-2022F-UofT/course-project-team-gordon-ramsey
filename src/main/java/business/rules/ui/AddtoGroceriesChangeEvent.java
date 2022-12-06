package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;

public class AddtoGroceriesChangeEvent extends ChangeEvent{
    public Recipe recipe;
    public AddtoGroceriesChangeEvent(Recipe recipe) {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
        this.recipe = recipe;
    }
}
