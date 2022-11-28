package business.rules.base;

import business.rules.dbs.RecipeDB;
import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import java.time.Duration;

public class UseCaseRemixRequest extends UseCaseRequest{

    private final Recipe toRemix;
    private final String newName;
    private final String newDescription;
    private final String[][] newIngredients;
    private final String newInstructions;
    private final String new_cook_time;
    private final String newYield;

    private final RecipeDB rdb;

    public UseCaseRemixRequest(int stage, Object toRemix, String newName, String newDescription,
                               String[][] newIngredients, String newInstructions,
                               String new_cook_time, String newYield, Object rdb) {
        super(stage);
        this.toRemix = (Recipe) toRemix;
        this.newName = newName;
        this.newDescription = newDescription;
        this.newIngredients = newIngredients;
        this.newInstructions = newInstructions;
        this.new_cook_time = new_cook_time;
        this.newYield = newYield;
        this.rdb = (RecipeDB) rdb;

    }

    public Recipe getToRemix() {
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