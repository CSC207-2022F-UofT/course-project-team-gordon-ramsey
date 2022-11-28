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

    public void addIngredient(Ingredient newItem){
        if (!exists(newItem)){
            ingredients.add(newItem);
        }
    }

    private boolean exists(Ingredient newItem){
        boolean exist = false;
        for (Ingredient item : ingredients) {
            if (newItem.equals(item)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
