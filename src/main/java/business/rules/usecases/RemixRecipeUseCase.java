package business.rules.usecases;

import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import java.time.Duration;

import business.rules.base.*;

public class RemixRecipeUseCase implements UseCase{

    private Recipe toRemix;
    private String newName, newDescription;
    private Ingredient[] newIngredients;
    private Instruction newInstruction;
    private Duration new_cook_time;

    public RemixRecipeUseCase(){}

    public RemixRecipeUseCase(Recipe recipe, String name, String description, Ingredient[] ingredients,
                        Instruction instruction, Duration cook_time){
        this.toRemix = recipe;
        this.newName = name;
        this.newDescription = description;
        this.newIngredients = ingredients;
        this.newInstruction = instruction;
        this.new_cook_time = cook_time;

    }

    public Recipe remix(){
        Recipe remixedRecipe = new Recipe(this.newName, this.newDescription, this.newIngredients,
                this.newInstruction, this.new_cook_time);
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
