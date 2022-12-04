package business.rules.base;

import business.rules.dbs.RecipeDB;

public class UseCaseAddRequest extends UseCaseRequest{

    private String Name;
    private String Description;
    private String[][] Ingredients;
    private String Instructions;
    private String newCookTime;
    private String newYield;
    private RecipeDB rdb;

    public UseCaseAddRequest(int stage, String name, String description, String[][] ingredients, String instructions,
                             String newCookTime, String newYield, RecipeDB rdb) {
        super(stage);
        this.Name = name;
        this.Description = description;
        this.Ingredients = ingredients;
        this.Instructions = instructions;
        this.newCookTime = newCookTime;
        this.newYield = newYield;
        this.rdb = rdb;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String[][] getIngredients() {
        return Ingredients;
    }

    public String getInstructions() {
        return Instructions;
    }

    public String getNewCookTime() {
        return newCookTime;
    }

    public String getNewYield() {
        return newYield;
    }

    public RecipeDB getRdb() {
        return rdb;
    }
}
