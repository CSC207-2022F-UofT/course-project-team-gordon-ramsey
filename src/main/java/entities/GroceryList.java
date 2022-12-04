package entities;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a grocery list.
 */
public class GroceryList implements Serializable{
    private List<Ingredient> ingredients;
    private String[][] collection;

    public GroceryList(){
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Ingredient[] getIngredients(){
        return (Ingredient[]) this.ingredients.toArray();
    }

    public String[][] getCollection(){
        this.collection = new String[this.ingredients.size()][2];
        for(int i = 0; i < this.collection.length; i++){
            this.collection[i][0] = this.ingredients.get(i).getName();
            this.collection[i][1] = this.ingredients.get(i).getDescription();
        }
        return this.collection;
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
