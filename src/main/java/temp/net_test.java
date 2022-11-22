package temp;

import external.interfaces.CLI;
import external.interfaces.NetReader;

import java.io.IOException;
import business.rules.api.APIDataResponse;
import business.rules.api.APIReader;
import business.rules.Presenter;
import business.rules.api.APIDataRequest;
import business.rules.api.APIRequest;
import business.rules.dbs.RecipeDB;
import entities.Recipe;

public class net_test {
    public static void main(String[] args) {
        CLI cli = new CLI();
        Presenter p = new Presenter(cli, new NetReader());
        Recipe[] arr = p.getRecipeDB().getRecipes("chicken masala", 0, 10);
        for(Recipe r : arr){
            if(r == null) continue;
            cli.showCollection(r.getCollection());
        }
    }
}
