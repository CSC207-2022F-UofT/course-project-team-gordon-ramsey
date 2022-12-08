package business.rules.dps;

import java.io.Serializable;
import java.util.List;

import entities.GroceryList;
import entities.Ingredient;

import java.util.ArrayList;

public class GroceryListDataPacket implements Serializable{
    public static GroceryList parse(GroceryListDataPacket gdp){
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        for(IngredientDataPacket idp : gdp.ingredients_dp){
            ingredients.add(IngredientDataPacket.parse(idp));
        }
        return new GroceryList(ingredients);
    }

    private List<IngredientDataPacket> ingredients_dp;

    public GroceryListDataPacket(GroceryList g){
        List<Ingredient> ingredients = g.getIngredientsList();
        this.ingredients_dp = new ArrayList<IngredientDataPacket>();
        for(Ingredient i : ingredients){
            this.ingredients_dp.add(new IngredientDataPacket(i));
        }
    }
}