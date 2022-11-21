package business.rules.dbs;

import entities.*;
import business.rules.Presenter;
import business.rules.api.*;
import business.rules.api.APIReader.REQUEST_TYPE;

import java.util.List;

public class RecipeDB extends DB{

    private int storage_limit, history_limit, history_end;
    private APIReader api;
    private Recipe[][] storage;
    private String[] keyword_map;

    public RecipeDB(int storage_limit, int history_limit, APIReader api) {
        this.storage_limit = storage_limit;
        this.history_limit = history_limit;
        this.api = api;
        this.storage = new Recipe[history_limit][storage_limit];
    }

    public RecipeDB(int historyLimit, APIReader api) {
        this.history_limit = historyLimit;
        this.storage_limit = 100;
        this.api = api;
        this.storage = new Recipe[history_limit][storage_limit];
    }

    public RecipeDB(APIReader api) {
        this.history_limit = 5;
        this.storage_limit = 100;
        this.api = api;
        this.storage = new Recipe[history_limit][storage_limit];
    }

    public Recipe[] getRecipes(String keyword, int size_atleast, Presenter presenter) {
        APIRequest api_rq = new APIRequest(keyword, REQUEST_TYPE.KEYWORD);
        List<String> recipes;
        int size = 0;
        while (size < size_atleast){
            try{
                recipes = ((APILinkResponse)this.api.request(api_rq, presenter)).data;
                size = recipes.size();
            }catch (ClassCastException e){
                presenter.showUser("An error occurred while reading from the API.");
            }
        }
        // add to local storage, give back size equivalent
        return null;
    }

    public int searchHistory(String keyword){
        return -1;
    }

    public Recipe[] searchStorage(int history_index){
        return null;
    }
}
