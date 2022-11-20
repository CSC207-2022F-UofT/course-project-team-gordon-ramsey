package business.rules;

import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import java.time.Duration;

public class AddNewRecipeUseCase {
    private String name, description;
    private Ingredient[] ingredients;
    private Instruction[] instructions;
    private Duration cook_time;

    public Recipe newRecipe(String name, String description, Ingredient[] ingredients, Instruction[] instructions, Duration cook_time){
        return new Recipe(name, description, ingredients, instructions, cook_time);
    }
}
