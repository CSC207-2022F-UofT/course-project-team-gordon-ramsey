package business.rules.base;

import business.rules.dbs.RecipeDB;
import entities.User;

public class UseCaseAddNewRecipeRequest extends UseCaseRequest {

    public String name, description, instructions, cook_time, yield;
    public String[][] ingredients;
    public RecipeDB rdb;
    private User user;

    public UseCaseAddNewRecipeRequest(int stage, RecipeDB rdb, User user, String name, String description,
                                      String[][] ingredients, String instructions, String cook_time,
                                      String yield) {
        super(stage);
        this.user = user;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = cook_time;
        this.rdb = rdb;
        this.yield = yield;

    }

}


