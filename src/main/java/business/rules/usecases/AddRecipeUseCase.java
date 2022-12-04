package business.rules.usecases;

import business.rules.base.*;
import business.rules.dbs.RecipeDB;
import entities.Ingredient;
import entities.Instruction;
import entities.Quantity;
import entities.Recipe;

import java.time.Duration;
import java.util.ArrayList;

public class AddRecipeUseCase implements UseCase {
    private final String addSuccess = "Recipe added successfully";
    private final String addFailure = "Failed to add recipe";

    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {
        //Reformat CLI info to necessary types
        UseCaseAddRequest ucra = (UseCaseAddRequest) ucr;
        String newName = ucra.getName();
        String newDesc = ucra.getDescription();
        String[][] ucrIngredients = ucra.getIngredients();
        Ingredient[] newIngredients = new Ingredient[0];
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        int i;
        for (i = 0; i < ucrIngredients.length; i++) {
            Ingredient newIngredient = new Ingredient(ucrIngredients[i][0], ucrIngredients[i][1],
                    new Quantity(Float.parseFloat(ucrIngredients[i][1]),ucrIngredients[i][2]));
            ingredients.add(newIngredient);
            newIngredients = (Ingredient[]) ingredients.toArray();
        }
        Instruction newInstructions = new Instruction(ucra.getInstructions());
        Duration newCookTime = Duration.parse(((UseCaseAddRequest) ucr).getNewCookTime());
        Float newYield = Float.valueOf(((UseCaseAddRequest) ucr).getNewYield());
        //Create new recipe and check if successfully added to RecipeDB
        Recipe newRecipe = new Recipe (newName, newDesc, newIngredients, newInstructions, newCookTime, newYield);
        RecipeDB rdb = ucra.getRdb();
        boolean resp = rdb.addRecipe(newRecipe);
        if (resp){
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING,
                    this.addSuccess);
        }
        else{
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addFailure);
        }
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "adding recipe";
    }
}
