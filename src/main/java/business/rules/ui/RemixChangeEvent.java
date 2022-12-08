package business.rules.ui;

import business.rules.UseCaseHandler;
/**
 * A Class that changes the state of the presenter when the user wants to remix a recipe
 *
 */
public class RemixChangeEvent extends ChangeEvent {
    /**
     * An array representing the recipe to be remixed and the new ingredients for remixed recipe
     */
    public String[][] toRemix, newIngredients;
    /**
     * Strings representing the additional attributes of the remixed recipe
     */
    public String newName, newDescription, newInstructions, newCookTime, newYield;

    /**
     *
     * @param toRemix the recipe the user wants to remix
     * @param newName the new name of the remixed recipe
     * @param newDescription the new description of the remixed recipe
     * @param newIngredients the new ingredients of the remixed recipe
     * @param newInstructions the new instructions for the remixed recipe
     * @param newCookTime the new cook time of the remixed recipe
     * @param newYield the new yield of the remixed recipe
     */
    public RemixChangeEvent(String[][] toRemix, String newName,
                            String newDescription, String[][] newIngredients, String newInstructions, String newCookTime,
                            String newYield) {
        super(UseCaseHandler.USE_CASE.REMIX_RECIPE_USECASE);
        this.toRemix = toRemix;
        this.newName = newName;
        this.newDescription = newDescription;
        this.newIngredients = newIngredients;
        this.newInstructions = newInstructions;
        this.newCookTime = newCookTime;
        this.newYield = newYield;
    }
}
