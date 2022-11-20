package business.rules.dbs;

import entities.*;
import business.rules.Presenter;
import business.rules.api.*;
import business.rules.api.APIReader.REQUEST_TYPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeDB extends DB{

    private int storageLimit = 20;
    private APIReader api;

    public RecipeDB(int storageLimit, APIReader api) {
        this.storageLimit = storageLimit;
        this.api = api;
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
}
