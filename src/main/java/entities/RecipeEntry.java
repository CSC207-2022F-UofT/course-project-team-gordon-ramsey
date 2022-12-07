package entities;

import java.time.Instant;

public class RecipeEntry extends Entry {
    public Recipe recipe;
    private String[][] collection;

    public RecipeEntry(Instant time, Recipe recipe) {
        super(time);
        this.recipe = recipe;
    }

    public Recipe getRecipe(){
        return this.recipe;
    }

    public String[][] getCollection(){
        String[][] recipe_info = this.recipe.getCollection();
        this.collection = new String[recipe_info.length + 1][];
        this.collection[0] = new String[2];
        this.collection[0][0] = "Recipe Addition Time";
        this.collection[0][1] = this.time.toString();
        for(int i = 1; i < this.collection.length; i++){
            this.collection[i] = recipe_info[i - 1];
        }
        return this.collection;
    }
}
