package business.rules.ui;

import business.rules.UseCaseHandler;

/**
 * A Class that changes the state of the presenter when the user selects a recipe from the available options
 * and possibly adds it as a favourite.
 *
 */
public class SelectChangeEvent extends ChangeEvent{
    /**
     * represents the recipe being selected by the user
     */

    public String recipe;
    /**
     * boolean representing if the recipe was/was not favourited by the user
     */
    public boolean favourite;

    /**
     *
     * @param recipe the recipe chosen by the user
     * @param favourite represents the option of the user to/not to favourite a recipe
     */
    public SelectChangeEvent(String recipe,
                             boolean favourite) {
        super(UseCaseHandler.USE_CASE.SELECT_RECIPE_USECASE);
        this.recipe = recipe;
        this.favourite = favourite;
    }
}
