package business.rules.base;

import business.rules.dbs.RecipeDB;
import entities.User;

/**
 * Manages request to add recipe
 */
public class UseCaseAddNewRecipeRequest extends UseCaseRequest {

    public String name, description, instructions, cook_time, yield;
    public String[][] ingredients;
    public RecipeDB rdb;

    /**
     * Constructor with all values
     * @param stage
     * @param rdb recipe DB
     * @param name recipe name
     * @param description recipe description
     * @param ingredients recipe ingredients
     * @param instructions recipe instructions
     * @param cook_time recipe cooking time
     * @param yield
     */
    public UseCaseAddNewRecipeRequest(int stage, RecipeDB rdb, User user, String name, String description,
                                      String[][] ingredients, String instructions, String cook_time,
                                      String yield) {
        super(stage);
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = cook_time;
        this.rdb = rdb;
        this.yield = yield;

    }

}


