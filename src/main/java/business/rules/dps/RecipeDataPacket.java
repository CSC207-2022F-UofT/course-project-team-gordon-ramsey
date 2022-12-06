package business.rules.dps;

import java.io.Serializable;
import java.time.Duration;

import entities.*;

public class RecipeDataPacket implements Serializable{
    public static Recipe parse(RecipeDataPacket rdp){
        Ingredient[] ingredients = new Ingredient[rdp.ingredients_dp.length];
        for(int i = 0; i < ingredients.length; i++){
            ingredients[i] = IngredientDataPacket.parse(rdp.ingredients_dp[i]);
        }
        return new Recipe(rdp.name, rdp.description, ingredients, InstructionDataPacket.parse(rdp.instruction_dp), rdp.cook_time, rdp.yield);
    }

    public String name, description;
    public IngredientDataPacket[] ingredients_dp;
    public InstructionDataPacket instruction_dp;
    public Duration cook_time;
    public float yield;

    public RecipeDataPacket(Recipe r){
        this.name = r.getName();
        this.description = r.getDescription();
        this.cook_time = r.getCookTime();
        this.yield = r.getYield();
        Ingredient[] ingredients = r.getIngredients();
        this.ingredients_dp = new IngredientDataPacket[ingredients.length];
        for(int i = 0; i < ingredients.length; i++){
            this.ingredients_dp[i] = new IngredientDataPacket(ingredients[i]);
        }
        this.instruction_dp = new InstructionDataPacket(r.getInstruction());
    }
}