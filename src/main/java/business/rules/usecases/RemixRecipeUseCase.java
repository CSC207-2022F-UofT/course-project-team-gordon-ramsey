package business.rules.usecases;

import business.rules.base.*;
import business.rules.dbs.RecipeDB;
import entities.Ingredient;
import entities.Instruction;
import entities.Quantity;
import entities.Recipe;
import java.time.Duration;
import java.util.ArrayList;

/**
 * A UseCase that handles remixing a Recipe and adding it to the RecipeDB based on input from the end user
 */

public class RemixRecipeUseCase implements UseCase {

    private final String remixSuccess = "Recipe Remixed Successfully";
    private final String addFailure = "Failed to add Remix";

    /**
     *
     * @param ucrParameter A UseCaseRequest with the required Recipe attributes to complete the remix
     * @return Returns a UseCaseResponse with the success/failure of each stage
     */
    public UseCaseResponse process(UseCaseRequest ucrParameter){
        UseCaseRemixRequest ucr = (UseCaseRemixRequest) ucrParameter;
        String ucrName = ucr.getNewName();
        String ucrDescription = ucr.getNewDescription();
        String[][] ucrIngredients = ucr.getNewIngredients();
        String ucrInstructions = ucr.getNewInstructions();
        Duration ucrCookTime = Duration.parse(ucr.getNew_cook_time());
        Float ucrYield = Float.valueOf(ucr.getNewYield());
        RecipeDB rdb = ucr.getRdb();
        Recipe toRemix = ucr.getToRemix();

        String newName;
        String newDescription;
        Ingredient[] newIngredients = new Ingredient[0];
        Instruction newInstructions = null;
        Duration newCookTime;
        Float newYield = null;

        if (ucrName == null){
            newName = toRemix.getName();
        }
        else {
            newName = ucrName;
        }
        if (ucrDescription == null){
            newDescription = toRemix.getDescription();
        }
        else {
            newDescription = ucrDescription;
        }
        if (ucrIngredients == null){
            newIngredients = toRemix.getIngredients();
        }
        else {
            int i;
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            for (i = 0; i < ucrIngredients.length; i++) {
                Ingredient newIngredient = new Ingredient(ucrIngredients[i][0], ucrIngredients[i][1],
                         new Quantity(Float.parseFloat(ucrIngredients[i][1]),ucrIngredients[i][2]));
                ingredients.add(newIngredient);
            newIngredients = (Ingredient[]) ingredients.toArray();
        }
        if (ucrInstructions == null){
            newInstructions = toRemix.getInstruction();
        }
        else {
            newInstructions = new Instruction(ucrInstructions);
            }
        }
        if (ucrCookTime == null){
            newCookTime = toRemix.getCookTime();
        }
        else {
            newCookTime = ucrCookTime;
        }
        Recipe newRecipe = new Recipe(newName, newDescription, newIngredients, newInstructions, newCookTime, newYield);
        boolean response = rdb.addRecipe(newRecipe);

        if (response){
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.remixSuccess);
        }
        else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, this.addFailure);
        }
    }

    /**
     *
     * @return Returns an int representing the final stage of this UseCase
     */
    public int getEndStage(){
        return 1;
    }

    /**
     *
     * @return Returns a string representing the work being done by this UseCase
     */
    public String getJob(){
        return "remixing recipe";
    }
}
