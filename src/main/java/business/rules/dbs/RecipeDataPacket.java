package business.rules.dbs;

import java.time.Duration;

import entities.*;

public class RecipeDataPacket {
    public static Recipe parse(RecipeDataPacket rdp){
        return new Recipe(rdp.name, rdp.description, rdp.ingredients, rdp.instruction, rdp.cook_time, rdp.yield);
    }

    public String name, description;
    public Ingredient[] ingredients;
    public Instruction instruction;
    public Duration cook_time;
    public float yield;

    public RecipeDataPacket(Recipe r){
        this.name = r.getName();
        this.description = r.getDescription();
        this.ingredients = r.getIngredients();
        this.instruction = r.getInstruction();
        this.cook_time = r.getCookTime();
        this.yield = r.getYield();
    }
}
