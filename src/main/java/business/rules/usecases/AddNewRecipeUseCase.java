package business.rules.usecases;

import business.rules.base.*;
import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import java.time.Duration;

public class AddNewRecipeUseCase {
    private String name, description;
    private Ingredient[] ingredients;
    private Instruction instructions;
    private Duration cook_time;

    public Recipe newRecipe(String name, String description, Ingredient[] ingredients, Instruction instructions, Duration cook_time, float yield){
        return new Recipe(name, description, ingredients, instructions, cook_time, yield);
    }

    public UseCaseResponse process(UseCaseRequest ucr){
        // initiate work here
        return null;
    }

    public int getEndStage(){
        return 1;
    }

    public String getJob(){
        return "adding new recipe";
    }
}