package useCases;

import java.time.Duration;

public class remixUseCase extends UseCase {
    private Recipe baseRecipe;
    private String name, description;
    private Ingredient[] ingredients;
    private Instruction[] instructions;
    private Duration cook_time;

    public remixUseCase(Recipe oldRecipe, String newName, String newDescription, Ingredient[] newIngredients,
                        Instruction[] newInstructions, Duration newCook_time){
        this.baseRecipe = oldRecipe;
        this.name = newName;
        this.ingredients = newIngredients;
        this.description = newDescription;
        this.instructions = newInstructions;
        this.cook_time = newCook_time;
    }
}
