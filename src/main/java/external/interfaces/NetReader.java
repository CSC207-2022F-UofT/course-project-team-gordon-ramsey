package external.interfaces;

import business.rules.Presenter;
import business.rules.api.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.IOException;

public class NetReader implements APIReader{
    private String response, query, next;
    private List<String> links;
    private int tmp;
    private int last_request_UID = -1;

    @Override
    public APIResponse request(APIRequest query, Presenter p){
        /**
         * PRECONDITION: query.data only alphanumeric if keyword request.
         * POSTCONDITION: links, next, last_request_UID remain untouched if recipe request.
         */
        if(query.type == REQUEST_TYPE.KEYWORD){
            if(last_request_UID == query.UID){
                if(this.next == null) return new APILinkResponse(this.links);
                this.query = this.next;
            }
            else{
                this.query = SEARCH_PREFIX + NO_INFO_FEILD_PREFIX + KEYWORD_PREFIX + query.data.trim().replaceAll(" ", PLUS);
                this.last_request_UID = query.UID;
                this.links = new ArrayList<String>();
            }
            try{
                loadLinks(new URL(this.query).openConnection().getInputStream());
            }catch(Exception e){
                p.showUser("Failed to retrieve information from server.");
            }
            return new APILinkResponse(this.links);
        }
        else if(query.type == REQUEST_TYPE.RECIPE){
            this.query = query.data;
            try{
                return new APIDataResponse(getRecipeData(new URL(this.query).openConnection().getInputStream()));
            }catch(Exception e){
                p.showUser("Failed to retrieve information from server.");
            }
        }
        return null;
    }

    private void loadLinks(InputStream stream) throws IOException{
        /**
         * this.links initilaized
         */
        this.response = "";
        while((this.tmp = stream.read()) != -1) this.response += (char)this.tmp;
        stream.close();
        if(this.response.indexOf(NEXT_KEYWORD) > 0){
            this.tmp = this.response.indexOf(HTTPS);
            this.next = this.response.substring(this.tmp, this.response.indexOf(QUOTE, this.tmp + 1));
        }
        else{
            this.tmp = -1;
            this.next = null;
        }
        while((this.tmp = this.response.indexOf(HTTPS, this.tmp + 1)) != -1){
            this.links.add(this.response.substring(this.tmp, this.response.indexOf(QUOTE, this.tmp + 1)));
        }
    }

    private String[] getRecipeData(InputStream stream) throws IOException{
        /**
         * String[] response : {<name>, <desc>, <ingredients>, <instructions>, <cook time>}
         * <ingredients> : <name>:<amount>:<unit><delimiter>
         */
        String recipe = "{\"recipe\":{\"ingredients\":[{\"text\":\"4 tbsp vegetable oil\",\"quantity\":4.0,\"measure\":\"tablespoon\",\"food\":\"vegetable oil\",\"weight\":56.0,\"foodCategory\":\"Oils\",\"foodId\":\"food_bt1mzi2ah2sfg8bv7no1qai83w8s\",\"image\":\"https://www.edamam.com/food-img/6e5/6e51a63a6300a8ea1b4c4cc68dfaba33.jpg\"},{\"text\":\"25g butter\",\"quantity\":25.0,\"measure\":\"gram\",\"food\":\"butter\",\"weight\":25.0,\"foodCategory\":\"Dairy\",\"foodId\":\"food_awz3iefajbk1fwahq9logahmgltj\",\"image\":\"https://www.edamam.com/food-img/713/71397239b670d88c04faa8d05035cab4.jpg\"},{\"text\":\"4 onions, roughly chopped\",\"quantity\":4.0,\"measure\":\"<unit>\",\"food\":\"onions\",\"weight\":500.0,\"foodCategory\":\"vegetables\",\"foodId\":\"food_bmrvi4ob4binw9a5m7l07amlfcoy\",\"image\":\"https://www.edamam.com/food-img/205/205e6bf2399b85d34741892ef91cc603.jpg\"},{\"text\":\"6 tbsp chicken tikka masala paste (use shop-bought or make your own – see recipe, below)\",\"quantity\":6.0,\"measure\":\"tablespoon\",\"food\":\"masala\",\"weight\":36.0,\"foodCategory\":\"Condiments and sauces\",\"foodId\":\"food_avzem7oamop4dsa5wb65obt5ldgi\",\"image\":\"https://www.edamam.com/food-img/c3f/c3f96d47d334b92f0120ff0b3a512ec3.jpg\"},{\"text\":\"2 red peppers, deseeded and cut into chunks\",\"quantity\":2.0,\"measure\":\"<unit>\",\"food\":\"red peppers\",\"weight\":238.0,\"foodCategory\":\"vegetables\",\"foodId\":\"food_a8g63g7ak6bnmvbu7agxibp4a0dy\",\"image\":\"https://www.edamam.com/food-img/4dc/4dc48b1a506d334b4ab6671b9d56a18f.jpeg\"},{\"text\":\"8 boneless, skinless chicken breasts, cut into 2.5cm cubes\",\"quantity\":8.0,\"measure\":\"<unit>\",\"food\":\"boneless, skinless chicken breasts\",\"weight\":2176.0,\"foodCategory\":\"Poultry\",\"foodId\":\"food_bdrxu94aj3x2djbpur8dhagfhkcn\",\"image\":\"https://www.edamam.com/food-img/da5/da510379d3650787338ca16fb69f4c94.jpg\"},{\"text\":\"2 x 400g cans chopped tomatoes\",\"quantity\":800.0,\"measure\":\"gram\",\"food\":\"tomatoes\",\"weight\":800.0,\"foodCategory\":\"canned vegetables\",\"foodId\":\"food_bnmkkwqa9h2p87bz171eoby0bsey\",\"image\":\"https://www.edamam.com/food-img/d4e/d4e8110d51db4311bc894167a8f77816.jpg\"},{\"text\":\"4 tbsp tomato purée\",\"quantity\":4.0,\"measure\":\"tablespoon\",\"food\":\"tomato purée\",\"weight\":60.4999999989772,\"foodCategory\":\"canned vegetables\",\"foodId\":\"food_aqqtb83adjyq8ybf51yo8bsjetdh\",\"image\":null},{\"text\":\"2-3 tbsp mango chutney\",\"quantity\":2.5,\"measure\":\"tablespoon\",\"food\":\"mango chutney\",\"weight\":50.0,\"foodCategory\":\"sugar syrups\",\"foodId\":\"food_b6nq9p3bqu2ydrbayvipqbpt22qx\",\"image\":\"https://www.edamam.com/food-img/55e/55e9572a1c35eeedc4900c5430f821b8.jpg\"},{\"text\":\"150ml double cream\",\"quantity\":150.0,\"measure\":\"milliliter\",\"food\":\"cream\",\"weight\":151.1486814772382,\"foodCategory\":\"Dairy\",\"foodId\":\"food_bvhbvd7bwy6a7wamfrmvmbmen1sz\",\"image\":\"https://www.edamam.com/food-img/484/4848d71f6a14dd5076083f5e17925420.jpg\"},{\"text\":\"150ml natural yogurt\",\"quantity\":150.0,\"measure\":\"milliliter\",\"food\":\"yogurt\",\"weight\":155.33316678659128,\"foodCategory\":\"yogurt\",\"foodId\":\"food_a79ojfkbgdeekgblqmky9bunr8f6\",\"image\":\"https://www.edamam.com/food-img/933/933eb3791b3a2175e007f1607d56b7e2.jpg\"},{\"text\":\"chopped coriander leaves, to serve\",\"quantity\":0.0,\"measure\":null,\"food\":\"coriander leaves\",\"weight\":42.47981848262807,\"foodCategory\":\"vegetables\",\"foodId\":\"food_alhzhuwb4lc7jnb5s6f02by60bzp\",\"image\":\"https://www.edamam.com/food-img/d57/d57e375b6ff99a90c7ee2b1990a1af36.jpg\"}]},\"_links\":{\"self\":{\"title\":\"Self\",\"href\":\"https://api.edamam.com/api/recipes/v2/46057930129e8ec50ad1b242c592accc?type=public&app_id=45a8cbca&app_key=4343d22a0cc52431a6d07ee2d73a5c46\"}}}";
        String link = "https://api.edamam.com/api/recipes/v2/46057930129e8ec50ad1b242c592accc?type=public&app_id=45a8cbca&app_key=4343d22a0cc52431a6d07ee2d73a5c46";
        stream = new URL(link).openConnection().getInputStream();
        this.response = "";
        while((this.tmp = stream.read()) != -1) this.response += (char)this.tmp;
        stream.close();
        // this.response : data in string.
        // Cole's recipe loader here
        ArrayList<String> ingredients = new ArrayList<String>();
        int index = -1;
            while ((index = recipe.indexOf("\"quantity\"", index + 1)) >= 0) {
                // do work here
                int qi = index + 11;
                int col = recipe.indexOf(":", qi);
                int end = recipe.indexOf(",", col + 1);
                String quantity = recipe.substring(col + 1, end).trim();
                ingredients.add(quantity);
            }
            while ((index = recipe.indexOf("\"measure\"", index + 1)) >= 0) {
                // do work here
                int mi = index + 10;
                int col = recipe.indexOf("\"", mi);
                int end = recipe.indexOf("\"", col + 1);
                String measure = recipe.substring(col + 1, end).trim();
                ingredients.add(measure);
            }
            while ((index = recipe.indexOf("\"food\"", index + 1)) >= 0) {
                // do work here
                int fi = index + 7;
                int col = recipe.indexOf("\"", fi);
                int end = recipe.indexOf("\"", col + 1);
                String food = recipe.substring(col + 1, end).trim();
                ingredients.add(food);
            }
            int start = 0;
            int arrayLength = ingredients.size();
            int jumpSize = arrayLength / 3;
            String[] newArr = new String[arrayLength];
            int i1 = 0,i2 = jumpSize,i3 = 2*jumpSize, i4 = 0;
            while (i3 < arrayLength) {
                newArr[i4] = ingredients.get(i1);
                newArr[i4+1] = ingredients.get(i2);
                newArr[i4+2] = ingredients.get(i3);
                i1++;
                i2++;
                i3++;
                i4 += 3;
            }
            return newArr;





        }


}