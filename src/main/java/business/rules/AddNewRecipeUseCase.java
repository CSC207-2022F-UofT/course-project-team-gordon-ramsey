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

    public AddNewRecipeUseCase (String name, String description, Ingredient[] ingredients, Instruction[] instructions, Duration cook_time){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = cook_time;
    }

    public Recipe newRecipe(){
        return new Recipe(this.name, this.description, this.ingredients,
                this.instructions, this.cook_time);
    }
}
