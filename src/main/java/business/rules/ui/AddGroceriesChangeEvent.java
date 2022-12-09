package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;
import entities.User;

/**
 * A Class that changes the state of the presenter when the user adds a recipe to their grocery list.
 */
public class AddGroceriesChangeEvent extends ChangeEvent{
    public Recipe recipe;
    public User user;

    /*
     * utiltiy no argument constructor so that UI can initiate change event.
     */
    public AddGroceriesChangeEvent() {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
        this.recipe = null;
        this.user = null;
    }

    /**
     * @param recipe a recipe to be added to the user's grocery list.
     * @param user concerned user.
     */
    public AddGroceriesChangeEvent(Recipe recipe, User user) {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
        this.recipe = recipe;
        this.user = user;
    }
}