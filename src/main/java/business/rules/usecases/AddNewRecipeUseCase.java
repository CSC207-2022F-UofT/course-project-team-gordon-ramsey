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
 * A UseCase that handles the work of adding a new recipe to the database
 */
public class AddNewRecipeUseCase implements UseCase {


    /** A UseCaseRequest whose attributes contain the info needed for process
     */
    private UseCaseAddNewRecipeRequest ucrnn;
    /** Final name and description for the new recipe
     */
    private String name, description;
    /** Array of Ingredients representing the ingredients, quantities, and measurements needed for the recipe
     */
    private Ingredient[] ingredients;
    /** Represents the instructions needed to cook the recipe
     */
    private Instruction instructions;
    /** Represents the amount of time needed to cook the recipe
     */
    private Duration cook_time;
    /** The active RecipeDB containing all Recipes
     */
    private RecipeDB rdb;
    /** A float representing the amount of servings this recipe yields
     */
    private float yield;

    /**
     *
     * @param ucr UseCaseRequest storing recipe name, description, ingredients, instructions, cook time, yield, as well
     *            as active user and active RecipeDB.
     * @return a UseCaseResponse with success enum and string if added successfully, and failure enum and string if not.
     */
    public UseCaseResponse process(UseCaseRequest ucr) {
        if (ucr instanceof UseCaseAddNewRecipeRequest) {
            this.ucrnn = (UseCaseAddNewRecipeRequest) ucr;
        } else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "Adding new recipe failed");
        }

        this.name = this.ucrnn.name;
        this.description = this.ucrnn.description;
        this.ingredients = convIngredients(this.ucrnn.ingredients);
        this.instructions = new Instruction(this.ucrnn.instructions);
        this.cook_time = Duration.parse(this.ucrnn.cook_time);
        this.yield = Float.parseFloat(this.ucrnn.yield);
        this.rdb = this.ucrnn.rdb;

        Recipe newR = new Recipe(name, description, ingredients, instructions, cook_time, yield);
        boolean addResponse = rdb.addRecipe(newR);
        if (addResponse) {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "New recipe added successfully");
        } else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "Adding new recipe failed");
        }

    }

    /**
     * Get the end stage of this UseCase. Default is 1 for a single step UseCase.
     * @return Integer representing final stage of this UseCase
     */
    @Override
    public int getEndStage() {
        return 1;
    }

    /**
     * Gets a string describing of the job being performed by this UseCase.
     * @return String describing job of UseCase.
     */
    @Override
    public String getJob() {
        return "adding recipe";
    }

    /**
     *
     * @param ucrIngredients Array of string arrays describing Ingredient list for this recipe
     * @return Ingredient object containing recipe's ingredients, quantities, and measurements.
     */
    private Ingredient[] convIngredients (String[][] ucrIngredients){
        ArrayList<Ingredient> ingredient = new ArrayList<Ingredient>();
        int i = 0;
        while (i < ucrIngredients.length) {
            Ingredient newIngredient = new Ingredient(ucrIngredients[i][0],
                    new Quantity(Float.parseFloat(ucrIngredients[i][1]),
                            ucrIngredients[i][2]));
            ingredient.add(newIngredient);
            i++;
        }
        return (Ingredient[]) ingredient.toArray();
    }
}



