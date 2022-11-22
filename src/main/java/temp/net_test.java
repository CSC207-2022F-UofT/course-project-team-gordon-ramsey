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
    public static void main(String[] args) throws IOException {
        NetReader api = new NetReader();
        String link = "https://api.edamam.com/api/recipes/v2/46057930129e8ec50ad1b242c592accc?type=public&app_id=45a8cbca&app_key=4343d22a0cc52431a6d07ee2d73a5c46";

        APIRequest req_link = new APIRequest("https://api.edamam.com/api/recipes/v2/46057930129e8ec50ad1b242c592accc?type=public&app_id=45a8cbca&app_key=4343d22a0cc52431a6d07ee2d73a5c46", REQUEST_TYPE.RECIPE);
        String[] arr = (String[])((APIDataResponse)api.request(req_link, null)).data;
        for(String i : arr){
            System.out.println(i);
        }
        //APIRequest req_link = new APIDataRequest("https://api.edamam.com/api/recipes/v2/46057930129e8ec50ad1b242c592accc?type=public&app_id=45a8cbca&app_key=4343d22a0cc52431a6d07ee2d73a5c46");
        //String[] arr = (String[])((APIDataResponse)api.request(req_link, null)).data;
        //for(String i : arr){
        //    System.out.println(i);
        //}
        System.out.println(api.getCookTime(link));
        System.out.println(api.getInstructions(link));
        //List<String> ret = (List<String>) api.request(req_link, null).data;
        //APIRequest req_recipe = new APIRequest(ret.get(0), REQUEST_TYPE.RECIPE);
        //System.out.println(((Recipe) api.request(req_recipe, null).data).getName());
    }
}
