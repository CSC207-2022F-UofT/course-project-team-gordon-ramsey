package entities;

import java.io.Serializable;
import java.time.Duration;

/**
 * Represents a recipe.
 */
public class Recipe implements Serializable{
    private String name, description;
    private Ingredient[] ingredients;
    private Instruction[] instructions;
    private Duration cook_time;

    public Recipe(String name, String description, Ingredient[] ingredients, Instruction[] instructions, Duration cook_time){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = cook_time;
    }

    public Recipe(String name, Ingredient[] ingredients, Instruction[] instructions, Duration cook_time){
        this.name = name;
        this.description = "";
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = cook_time;
    }

    public Recipe(String name, String description, Ingredient[] ingredients, Instruction[] instructions){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = Duration.ZERO;
    }

    public Recipe(String name, Ingredient[] ingredients, Instruction[] instructions){
        this.name = name;
        this.description = "";
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cook_time = Duration.ZERO;
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

    public Instruction[] getInstructions(){
        return this.instructions;
    }

    public Duration getCookTime(){
        return this.cook_time;
    }
}