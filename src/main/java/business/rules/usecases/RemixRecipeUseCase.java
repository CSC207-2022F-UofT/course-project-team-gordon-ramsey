package business.rules.usecases;

import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import java.time.Duration;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;

public class RemixRecipeUseCase implements UseCase{

    private Recipe toRemix;
    private String newName, newDescription;
    private Ingredient[] newIngredients;
    private Instruction[] newInstructions;
    private Duration new_cook_time;

    public RemixRecipeUseCase(){}

    public RemixRecipeUseCase(Recipe recipe, String name, String description, Ingredient[] ingredients,
                        Instruction[] instructions, Duration cook_time){
        this.toRemix = recipe;
        this.newName = name;
        this.newDescription = description;
        this.newIngredients = ingredients;
        this.newInstructions = instructions;
        this.new_cook_time = cook_time;

    }

    public Recipe remix(){
        Recipe remixedRecipe = new Recipe(this.newName, this.newDescription, this.newIngredients,
                this.newInstructions, this.new_cook_time);
        return remixedRecipe;
    }

    public UseCaseResponse process(UseCaseRequest ucr){
        // initiate work here
        return null;
    }

    public int getEndStage(){
        return 1;
    }

    public String getJob(){
        return "remixing recipe";
    }
}
