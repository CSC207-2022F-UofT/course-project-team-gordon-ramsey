package entities;

import java.time.Duration;

/**
 * Represents a recipe.
 */
public class Recipe{
    public static final int DESCRIPTION_INDEX = 1, INGREDIENTS_INDEX = 2, COOKTIME_INDEX = 4, YIELD_INDEX = 5;
    public static Recipe parse(String[][] collection){
        String name = collection[0][1];
        String description = collection[1][1];
        Ingredient[] ingredients = new Ingredient[collection[2].length - 1];
        for(int i = 1; i < collection[2].length; i++){
            ingredients[i - 1] = Ingredient.parse(collection[2][i]);
            if(ingredients[i - 1] == null) return null;
        }
        Instruction instructions = new Instruction(collection[3][1]);
        try{
            collection[4][1] = collection[4][1].toLowerCase();
            int factor = 1;
            if(collection[4][1].indexOf("hours") >= 0 || collection[4][1].indexOf("hrs") >= 0)factor = 60;
            Duration cook_time = Duration.ofMinutes(factor * Integer.parseInt(collection[4][1].split(" ")[0]));
            float yield = Float.parseFloat(collection[5][1]);
            return new Recipe(name, description, ingredients, instructions, cook_time, yield);
        } catch(Exception e) {return null;}
    }

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