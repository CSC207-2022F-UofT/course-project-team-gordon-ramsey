package business.rules.base;

import business.rules.dbs.RecipeDB;
import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import java.time.Duration;

/**
 * Manages request to remix recipe
 */
public class UseCaseRemixRequest extends UseCaseRequest{

    private final Object[][] toRemix;
    private final String newName;
    private final String newDescription;
    private final String[][] newIngredients;
    private final String newInstructions;
    private final String new_cook_time;
    private final String newYield;
    private final RecipeDB rdb;

    /**
     *
     * @param stage stage of useCase request
     * @param toRemix recipe user wants to remix
     * @param newName new recipe name
     * @param newDescription new recipe description
     * @param newIngredients new recipe ingredients
     * @param newInstructions new recipe instructions
     * @param new_cook_time new recipe cooking time
     * @param newYield serving
     * @param rdb recipe DB
     */
    public UseCaseRemixRequest(int stage, Object[][] toRemix, String newName, String newDescription,
                               String[][] newIngredients, String newInstructions,
                               String new_cook_time, String newYield, RecipeDB rdb) {
        super(stage);
        this.toRemix = toRemix;
        this.newName = newName;
        this.newDescription = newDescription;
        this.newIngredients = newIngredients;
        this.newInstructions = newInstructions;
        this.new_cook_time = new_cook_time;
        this.newYield = newYield;
        this.rdb = rdb;

    }

    public Object[][] getToRemix() {
        return toRemix;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public String[][] getNewIngredients() {
        return newIngredients;
    }

    public String getNewInstructions() {
        return newInstructions;
    }

    public String getNew_cook_time() {
        return new_cook_time;
    }

    public String getNewYield() {
        return newYield;
    }

    public RecipeDB getRdb() {
        return rdb;
    }
}
