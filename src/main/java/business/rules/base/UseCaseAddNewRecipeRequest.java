package business.rules.base;


import entities.Ingredient;
import entities.Instruction;
import entities.User;

import java.time.Duration;

public class UseCaseAddNewRecipeRequest extends UseCaseRequest{

    private String name, description;
    private Ingredient[] ingredients;
    private Instruction[] instructions;
    private Duration cook_time;
    private User user;

    public UseCaseAddNewRecipeRequest(User user, String name, String description, Ingredient[] ingredients, Instruction[] instructions, Duration cook_time, int stage) {
        super(stage);
        this.user = user;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = cook_time;
    }

}
