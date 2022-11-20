package temp;

import external.interfaces.NetReader;

import java.util.List;

import business.rules.api.APIReader;
import business.rules.api.APIRequest;
import business.rules.api.APIReader.REQUEST_TYPE;
import entities.Recipe;

public class net_test {
    public static void main(String[] args) {
        APIReader api = new NetReader();
        APIRequest req_link = new APIRequest("chicken tikka masala", REQUEST_TYPE.KEYWORD);
        //List<String> ret = (List<String>) api.request(req_link, null).data;
        //APIRequest req_recipe = new APIRequest(ret.get(0), REQUEST_TYPE.RECIPE);
        //System.out.println(((Recipe) api.request(req_recipe, null).data).getName());
    }
}
