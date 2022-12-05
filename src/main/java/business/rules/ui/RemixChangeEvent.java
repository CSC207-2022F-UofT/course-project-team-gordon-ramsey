package business.rules.ui;

import business.rules.UseCaseHandler;

public class RemixChangeEvent extends ChangeEvent {
    public String[][] toRemix, newIngredients;
    public String newName, newDescription, newInstructions, newCookTime, newYield;

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
