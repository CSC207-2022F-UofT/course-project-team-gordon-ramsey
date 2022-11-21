package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a grocery list.
 */
public class GroceryList implements Serializable{
    private List<Ingredient> ingredients;

    public GroceryList(Ingredient[] ingredients){
        this.ingredients = new ArrayList<Ingredient>();
        Map<String, Integer> name_map = new HashMap<String, Integer>();
        boolean found;
        for(int i = 0; i < ingredients.length; i++){
            found = false;
            for(String name : name_map.keySet()){
                if(name.equalsIgnoreCase(ingredients[i].getName())){
                    this.ingredients.get(name_map.get(name)).getQuantity().add(ingredients[i].getQuantity().getAmount());
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
        boolean found;
        for(Ingredient i : ingredients){
            found = false;
            for(String name : name_map.keySet()){
                if(name.equalsIgnoreCase(i.getName())){
                    this.ingredients.get(name_map.get(name)); 
                    this.ingredients.get(name_map.get(name)).getQuantity().add(i.getQuantity().getAmount());
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