package temp;

import external.interfaces.CLI;
import external.interfaces.NetReader;


import business.rules.Presenter;
import entities.Recipe;

public class net_test {
    public static void main(String[] args) {

        CLI cli = new CLI(null);
        Presenter p = Presenter.buildPresenter(cli, new NetReader(null));
        Recipe[] arr = p.getRecipeDB().getRecipes("pasta", 0, 300, true);
        int c = 0;
        for(Recipe r : arr){
            if(r == null) continue;
            //cli.showCollection(r.getCollection());
            //System.out.println("---------------------------------");
            c++;
        }
        System.out.println("Recipes loaded : " + c);
        p.close();
        //APIRequest req_link = new APIDataRequest("https://api.edamam.com/api/recipes/v2/46057930129e8ec50ad1b242c592accc?type=public&app_id=45a8cbca&app_key=4343d22a0cc52431a6d07ee2d73a5c46");
        //String[] arr = (String[])((APIDataResponse)api.request(req_link, null)).data;
        //for(String i : arr){
        //    System.out.println(i);
        //}
        //List<String> ret = (List<String>) api.request(req_link, null).data;
        //APIRequest req_recipe = new APIRequest(ret.get(0), REQUEST_TYPE.RECIPE);
        //System.out.println(((Recipe) api.request(req_recipe, null).data).getName());
    }
}
