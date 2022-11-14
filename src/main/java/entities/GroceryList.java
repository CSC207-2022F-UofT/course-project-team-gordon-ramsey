package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a grocery list.
 */
public class GroceryList{
    private List<Ingredient> ingredients;

    public GroceryList(Ingredient[] ingredients){
        this.ingredients = new ArrayList<Ingredient>();
        Map<String, Integer> name_map = new HashMap<String, Integer>();
        Ingredient tmp;
        boolean found;
        for(int i = 0; i < ingredients.length; i++){
            found = false;
            for(String name : names.keySet()){
                if(name.equalsIgnoreCase(ingredients[i].getName())){
                    tmp = this.ingredients.get(name_map.get(name)); 
                    tmp.setQuantity(Quantity.add(ingredients[i].getQuantity(), tmp.getQuantity()));
                    found = true;
                    break;
                }
            }
            if(!found){
                this.ingredients.add(ingredients[i]);
                name_map.put(ingredients[i].getName(), this.ingredients.size() - 1);
            }
        }
    }

    public GroceryList(List<Ingredient> ingredients){
        this.ingredients = new ArrayList<Ingredient>();
        Map<String, Integer> name_map = new HashMap<String, Integer>();
        Ingredient tmp;
        boolean found;
        for(Ingredient i : ingredients){
            found = false;
            for(String name : names.keySet()){
                if(name.equalsIgnoreCase(i.getName())){
                    tmp = this.ingredients.get(name_map.get(name)); 
                    tmp.setQuantity(Quantity.add(i.getQuantity(), tmp.getQuantity()));
                    found = true;
                    break;
                }
            }
            if(!found){
                this.ingredients.add(i);
                name_map.put(i.getName(), this.ingredients.size() - 1);
            }
        }
    }

    public GroceryList(){
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Ingredient[] getIngredients(){
        return (Ingredient[]) this.ingredients.toArray();
    }
}