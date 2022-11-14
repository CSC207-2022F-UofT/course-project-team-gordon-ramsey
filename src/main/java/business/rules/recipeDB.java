package business.rules;

import entities.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class recipeDB {

    private int storageLimit = 20;
    private APIReader api;

    public recipeDB(int storageLimit, APIReader api) {
        this.storageLimit = storageLimit;
        this.api = api;
    }

    public Recipe[] getRecipes(String keyword, int size) {
        APIResponse api_response;
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        int loaded = 0;
        while (loaded < size) {
            api_response = this.api.request(new APIRequest(keyword));
            if (api_response.data.length > 0) {
                loaded += api_response.data.length;
                recipes.addAll(List.of(api_response.data));
            } else break;
        }
        while (loaded > size) {
            loaded--;
            recipes.remove(recipes.size() - 1);
        }
        return (Recipe[]) recipes.toArray();
    }

}


