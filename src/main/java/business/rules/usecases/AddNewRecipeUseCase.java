package business.rules.usecases;

import business.rules.base.*;
import business.rules.base.request.UseCaseAddNewRecipeRequest;
import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;
import business.rules.base.response.UseCaseStringResponse;
import business.rules.dbs.RecipeDB;
import entities.Ingredient;
import entities.Instruction;
import entities.Quantity;
import entities.Recipe;

import java.time.Duration;
import java.util.ArrayList;

public class AddNewRecipeUseCase implements UseCase {
    private UseCaseAddNewRecipeRequest ucrnn;
    private String name, description;
    private Ingredient[] ingredients;
    private Instruction instructions;
    private Duration cook_time;
    private RecipeDB rdb;
    private float yield;

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
        boolean addResponse = rdb.addLocalRecipe(newR);
        if (addResponse) {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.SUCCESS,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "New recipe added successfully");
        } else {
            return new UseCaseStringResponse(UseCaseResponse.RETURN_CODE.FAILURE,
                    UseCaseResponse.ACTION_CODE.SHOW_DATA_STRING, "Adding new recipe failed");
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
