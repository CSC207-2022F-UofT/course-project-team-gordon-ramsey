package entities;

import java.io.Serializable;
import java.time.Duration;

/**
 * Represents a recipe.
 */
public class Recipe implements Serializable{
    private String name, description;
    private Ingredient[] ingredients;
    private Instruction instruction;
    private Duration cook_time;
    private float yield;
    private String[][] collection;

    public Recipe(String name, String description, Ingredient[] ingredients, Instruction instruction, Duration cook_time, float yield){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.cook_time = cook_time;
        this.yield = yield;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Ingredient[] getIngredients(){
        return this.ingredients;
    }

    public Instruction getInstruction(){
        return this.instruction;
    }

    public Duration getCookTime(){
        return this.cook_time;
    }

    public float getYield(){
        return this.yield;
    }

    public String[][] getCollection(){
        this.collection = new String[6][];
        this.collection[0] = new String[2];
        this.collection[1] = new String[2];
        this.collection[2] = new String[this.ingredients.length + 1];
        this.collection[3] = new String[2];
        this.collection[4] = new String[2];
        this.collection[5] = new String[2];
        this.collection[0][0] = "Name";
        this.collection[1][0] = "Description";
        this.collection[2][0] = "Ingredients";
        this.collection[3][0] = "Instructions";
        this.collection[4][0] = "Cooking Time";
        this.collection[5][0] = "Yield";
        this.collection[0][1] = this.name;
        this.collection[1][1] = this.description;
        this.collection[3][1] = this.instruction.getInstruction();
        this.collection[4][1] = this.cook_time.toMinutes() + " minutes";
        this.collection[5][1] = "" + this.yield;
        for(int i = 0; i < this.ingredients.length; i++){
            this.collection[2][i + 1] = this.ingredients[i].getDescription();
        }
        return this.collection;
    }

}