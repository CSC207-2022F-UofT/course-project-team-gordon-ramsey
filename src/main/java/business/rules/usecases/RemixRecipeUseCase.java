package business.rules.usecases;

import business.rules.dbs.RecipeDB;
import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import java.time.Duration;

import business.rules.UseCase;
import business.rules.UseCaseRequest;
import business.rules.UseCaseResponse;
import business.rules.UseCaseResponse.ACTION_CODE;
import business.rules.UseCaseResponse.RETURN_CODE;

public class RemixRecipeUseCase implements UseCase{

    private String[] remixSuccess = {"Recipe Remixed Successfully"};
    private String[] addFailure = {"Failed to add Remix"};

    public UseCaseResponse process(UseCaseRequest ucr){
        Recipe toRemix = (Recipe) ucr.data[0];
        String newName = (String) ucr.data[1];
        String newDescription = (String) ucr.data[2];
        Ingredient[] newIngredients = (Ingredient[]) ucr.data[3];
        Instruction[] newInstructions = (Instruction[]) ucr.data[4];
        Duration new_cook_time = (Duration) ucr.data[5];

        if (newName == null){
            newName = toRemix.getName();
        }
        if (newDescription == null){
            newDescription = toRemix.getDescription();
        }
        if (newIngredients == null){
            newIngredients = toRemix.getIngredients();
        }
        if (newInstructions == null){
            newInstructions = toRemix.getInstructions();
        }
        if (new_cook_time == null){
            new_cook_time = toRemix.getCookTime();
        }
        Recipe newRecipe = new Recipe(newName, newDescription, newIngredients, newInstructions, new_cook_time);
        boolean response = RecipeDB.addRecipe(newRecipe);

        if (response){
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    this.remixSuccess);
        }
        else {
            return new UseCaseResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    this.addFailure);
        }
    }

    public int getEndStage(){
        return 1;
    }

    public String getJob(){
        return "remixing recipe";
    }
}
