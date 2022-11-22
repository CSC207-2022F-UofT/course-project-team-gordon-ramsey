package business.rules.dbs;

import entities.*;
import business.rules.Presenter;
import business.rules.api.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RecipeDB extends DB{
    private int storage_limit;
    private APIReader api;
    private Presenter presenter;

    public RecipeDB(int storage_limit, APIReader api, Presenter presenter) {
        this.storage_limit = storage_limit;
        this.api = api;
        this.presenter = presenter;
    }

    public RecipeDB(APIReader api, Presenter presenter) {
        this.storage_limit = 500;
        this.api = api;
        this.presenter = presenter;
    }

    public Recipe[] getRecipes(String keyword, int skip_atleast, int size_atleast) {   // needs testing
        /**
         * PRECONDITION: skip <= max(size_atleast, storage_limit)
         */
        List<String> links = new ArrayList<String>();
        APILinkRequest api_rq = new APILinkRequest(keyword, skip_atleast);
        int size = 0;
        while(size < Math.min(size_atleast, this.storage_limit)){
            links = this.api.request(api_rq, this.presenter).data;
            if(links == null) return null;
            else if(size >= links.size()) break;
            size = links.size();
        }
        String[][] info = this.api.request(new APIDataRequest(links), this.presenter).data;
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
}