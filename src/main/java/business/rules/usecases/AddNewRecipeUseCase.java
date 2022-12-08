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
 * Add new recipe to the recipe database
 */
public class AddNewRecipeUseCase implements UseCase {
    private String name, description;
    private Ingredient[] ingredients;
    private Instruction instructions;
    private Duration cook_time;
    private RecipeDB rdb;
    private float yield;


    /**
     * Checks if the use case request is for adding recipe, if true creates new recipe and adds to recipe DB
     * @param ucr use case request from user
     * @return UseCaseResponse of success or failure
     */
    public UseCaseResponse process(UseCaseRequest ucr) {
        UseCaseAddNewRecipeRequest ucrnn;
        if (ucr instanceof UseCaseAddNewRecipeRequest) {
            ucrnn = (UseCaseAddNewRecipeRequest) ucr;
        } else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE, UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "Adding new recipe failed");
        }

        this.name = ucrnn.name;
        this.description = ucrnn.description;
        this.ingredients = convIngredients(ucrnn.ingredients);
        this.instructions = new Instruction(ucrnn.instructions);
        this.cook_time = Duration.parse(ucrnn.cook_time);
        this.yield = Float.parseFloat(ucrnn.yield);
        this.rdb = ucrnn.rdb;

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
     * Override for getEndStage
     * @return int representing the final stage of UseCase
     */
    @Override
    public int getEndStage() {
        return 1;
    }

    /**
     * Override for getJob
     * @return String of UseCase action "adding recipe"
     */
    @Override
    public String getJob() {
        return "adding recipe";
    }

    /**
     * Coverts the ingredient list from String[][] to Ingredient[]
     * @param ucrIngredients ingredients list of type String[][]
     * @return an ingredients list in Ingredient[] type
     */
    private Ingredient[] convIngredients (String[][] ucrIngredients){
        ArrayList<Ingredient> ingredient = new ArrayList<Ingredient>();
        int i = 0;
        while (i < ucrIngredients.length) {
            Ingredient newIngredient = new Ingredient(ucrIngredients[i][0], new Quantity(Float.parseFloat(ucrIngredients[i][1]), ucrIngredients[i][2]));
            ingredient.add(newIngredient);
            i++;
        }
        return (Ingredient[]) ingredient.toArray();
    }
}



