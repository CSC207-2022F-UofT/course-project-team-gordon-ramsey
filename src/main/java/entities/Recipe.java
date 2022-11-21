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
    private String[][] collection;

    public Recipe(String name, String description, Ingredient[] ingredients, Instruction instruction, Duration cook_time){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.cook_time = cook_time;
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

    public String[][] getCollection(){
        this.collection = new String[5][];
        this.collection[0] = new String[2];
        this.collection[1] = new String[2];
        this.collection[2] = new String[ingredients.length + 1];
        this.collection[3] = new String[2];
        this.collection[4] = new String[2];
        this.collection[0][0] = "Name";
        this.collection[1][0] = "Description";
        this.collection[2][0] = "Ingredients";
        this.collection[3][0] = "Instructions";
        this.collection[4][0] = "Cooking Time";
        this.collection[0][1] = name;
        this.collection[1][1] = description;
        this.collection[3][1] = instruction.getInstruction();
        this.collection[4][1] = cook_time.toMinutes() + " minutes";
        for(int i = 0; i < ingredients.length; i++){
            this.collection[2][i + 1] = ingredients[i].getDescription();
        }
        return this.collection;
    }

}