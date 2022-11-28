package entities;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a grocery list.
 */
public class GroceryList implements Serializable{
    private List<Ingredient> ingredients;

    public GroceryList(){
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Ingredient[] getIngredients(){
        return (Ingredient[]) this.ingredients.toArray();
    }
}