package business.rules.ui;

import business.rules.UseCaseHandler;

public class AddNewRecipeChangeEvent extends ChangeEvent{
    public String newName, newDescription, newInstructions, newCookTime, newYield;
    public String[][] newIngredients;

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
