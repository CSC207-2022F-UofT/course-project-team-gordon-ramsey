package business.rules.dbs;

import entities.*;
import business.rules.Presenter;
import business.rules.api.*;

import java.util.ArrayList;
import java.util.List;

public class RecipeDB extends DB{

    private int storage_limit;
    private APIReader api;

    public RecipeDB(int storage_limit, APIReader api) {
        this.storage_limit = storage_limit;
        this.api = api;
    }

    public RecipeDB(APIReader api) {
        this.storage_limit = 500;
        this.api = api;
    }

    public Recipe[] getRecipes(String keyword, int skip_atleast, int size_atleast, Presenter presenter) {
        /**
         * PRECONDITION: skip <= max(size_atleast, storage_limit)
         */

        List<String> links = new ArrayList<String>();
        APILinkRequest api_rq = new APILinkRequest(keyword, skip_atleast);
        int size = 0;
        while(size < Math.max(size_atleast, this.storage_limit)){
            links = this.api.request(api_rq, presenter).data;
            if(size >= links.size()) break;
            size = links.size();
        }
        String[][] info = this.api.request(new APIDataRequest(links), presenter).data;
        for(int i = 0; i < info.length; i++){

            for(int j = 4; j < info[i].length; j += 3){

                ;
            }
        }
        return null;
    }
}
