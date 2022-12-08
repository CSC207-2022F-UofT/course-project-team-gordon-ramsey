package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;
/**
 * A Class that changes the state of the presenter when the user adds a recipe to their grocery list.
 *
 */
public class AddtoGroceriesChangeEvent extends ChangeEvent{
    /**
     * Represents the recipe being added to the grocery list
     */
    public Recipe recipe;

    /**
     *
     * @param recipe a recipe to be added to the user's grocery list
     */
    public AddtoGroceriesChangeEvent(Recipe recipe) {
        super(UseCaseHandler.USE_CASE.ADD_TO_GROCERIES_USECASE);
        this.recipe = recipe;
    }
}
