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

    /**
     *
     * @param ingredients Ingredients to be included in the grocery list
     */
    public GroceryList(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    public Ingredient[] getIngredients(){
        return (Ingredient[]) this.ingredients.toArray();
    }

    public List<Ingredient> getIngredientsList(){
        return this.ingredients;
    }

    public String[][] getCollection(){
        this.collection = new String[this.ingredients.size()][2];
        for(int i = 0; i < this.collection.length; i++){
            this.collection[i][0] = this.ingredients.get(i).getName();
            this.collection[i][1] = this.ingredients.get(i).getDescription();
        }
        return this.collection;
    }

    /**
     * Adds an ingredient to the grocery list (only if it doesn't already exist in the list).
     * @param newItem an Ingredient object to be added to the grocery list
     */
    public void addIngredient(Ingredient newItem){
        int index = this.indexOf(newItem);
        if(index == -1)ingredients.add(newItem);
        else{
            String new_item_unit = newItem.getQuantity().getUnit();
            String old_item_unit = this.ingredients.get(index).getQuantity().getUnit();
            if(old_item_unit.equalsIgnoreCase(new_item_unit)){
                this.ingredients.get(index).getQuantity().add(newItem.getQuantity().getAmount());
            }
            else this.ingredients.add(new Ingredient(newItem.getName(), 
                                                     newItem.getDescription() + " (repeated)",
                                                     newItem.getQuantity()));
        }
    }

    public void addIngredients(Ingredient newItems[]){
        for(Ingredient i : newItems){
            this.addIngredient(i);
        }
    }

    /**
     * returns index of newItem in grocery list, -1 if not present.
     * @param newItem an Ingredient object
     * @return returns index of matching ingredient, otherwise -1.
     */
    private int indexOf(Ingredient newItem){
        int index = -1;
        for (int i = 0; i < this.ingredients.size(); i++) {
            if (newItem.getName().equalsIgnoreCase(this.ingredients.get(i).getName())){
                index = i;
                break;
            }
        }
        return index;
    }
}