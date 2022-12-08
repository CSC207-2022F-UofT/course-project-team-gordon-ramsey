package business.rules.ui;

import business.rules.UseCaseHandler;

/**
 * A Class that changes the state of the presenter when the user adds a new recipe.
 *
 */
public class AddNewRecipeChangeEvent extends ChangeEvent{
    /**
     * String representing the attributes of the remixed recipe
     */
    public String newName, newDescription, newInstructions, newCookTime, newYield;
    /**
     * String array with information of ingredients in new recipe
     */
    public String[][] newIngredients;

    /**
     *
     * @param newName represents the name of the new recipe
     * @param newDescription a description of the new recipe being added
     * @param newIngredients represents the ingredients which form the new recipe
     * @param newInstructions a set of instructions to follow for the new recipe
     * @param newCookTime the cook time for the new recipe
     * @param newYield the yield of the recipe being added
     */
    public AddNewRecipeChangeEvent(String newName,
                                   String newDescription, String[][] newIngredients,
                                   String newInstructions, String newCookTime,
                                   String newYield){
        super(UseCaseHandler.USE_CASE.CREATE_USER_USECASE);
        this.newName = newName;
        this.newDescription = newDescription;
        this.newIngredients = newIngredients;
        this.newInstructions = newInstructions;
        this.newCookTime = newCookTime;
        this.newYield = newYield;
    }
}
