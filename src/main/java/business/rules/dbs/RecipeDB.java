package business.rules.dbs;

import entities.*;
import business.rules.api.*;

import java.time.Duration;

public class RecipeDB implements DB{
    private int storage_limit;
    private APIReader api;

    public RecipeDB(int storage_limit, APIReader api) {
        this.storage_limit = storage_limit;
        this.api = api;
    }

    public RecipeDB(APIReader api) {
        this.storage_limit = 200;
        this.api = api;
    }

    public Recipe[] getRecipes(String keyword, int skip_atleast, int size_atleast, boolean verbose) {
        /**
         * PRECONDITION: skip <= max(size_atleast, storage_limit)
         */
        String[][] info = this.api.request(new APIRequest(keyword, skip_atleast, Math.min(size_atleast, this.storage_limit)), verbose).data;
        if(info == null) return new Recipe[0];
        Recipe[] recipes = new Recipe[info.length];
        for(int i = 0; i < info.length; i++){
            if(info[i] ==  null) break;
            Ingredient[] ingredients = new Ingredient[(info[i].length - 5) / 4];
            for(int j = 5, k = 0; j < info[i].length; j += 4, k += 1){
                ingredients[k] = new Ingredient(info[i][j + 3], info[i][j], new Quantity(Float.parseFloat(info[i][j + 1]), info[i][j + 2]));
            }
            recipes[i] = new Recipe(info[i][0], info[i][1], ingredients, new Instruction(info[i][2]), Duration.ofMinutes((long)Float.parseFloat(info[i][3])), Float.parseFloat(info[i][4]));
        }
        return recipes;
    }

    public void close(){
        this.api.stopClocks();
    }
}