package external.interfaces;

import business.rules.Presenter;
import business.rules.api.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.IOException;

public class NetReader implements APIReader {
    private String response, query, next;
    private List<String> links;
    private int tmp;
    private int last_request_UID = -1;
    private InputStream in;

    public NetReader(){
        this.next = "";
    }

    public APILinkResponse request(APILinkRequest query, Presenter p) {
        /**
         * PRECONDITION: query.keyword only alphanumeric.
         */
        if (last_request_UID == query.UID) {
            if (this.next == null) return new APILinkResponse(this.links);
            this.query = this.next;
        } else {
            this.query = SEARCH_PREFIX + NO_INFO_FEILD_PREFIX + KEYWORD_PREFIX + query.keyword.trim().replaceAll(" ", PLUS);
            this.last_request_UID = query.UID;
            this.links = new ArrayList<String>();
        }
        if(!this.readData(this.query)) p.showUser("Failed to retrieve information from server.");
        if (this.response.indexOf(NEXT_KEYWORD) > 0) {
            this.tmp = this.response.indexOf(HTTPS);
            this.next = this.response.substring(this.tmp, this.response.indexOf(QUOTE, this.tmp + 1));
        } else {
            this.tmp = -1;
            this.next = null;
        }
        if(query.skip > 0){
            query.skip -= 20;
            return this.request(query, p);
        }
        while ((this.tmp = this.response.indexOf(HTTPS, this.tmp + 1)) != -1) {
            this.links.add(this.response.substring(this.tmp, this.response.indexOf(QUOTE, this.tmp + 1)));
        }
        return new APILinkResponse(this.links);
    }

    public APIDataResponse request(APIDataRequest query, Presenter p) {
        /**
         * POSTCONDITION: links, next, last_request_UID remain untouched.
         */
        String[][] info = new String[query.links.size()][];
        for(int i = 0; i < query.links.size(); i++){
            this.query = query.links.get(i);
            info[i] = getRecipeData(p);
        }
        return new APIDataResponse(info);
    }

    private boolean readData(String link) {
        /**
         * loads page on this.response
         */
        try{
            in = new URL(link).openConnection().getInputStream();
            this.response = "";
            while ((this.tmp = in.read()) != -1) this.response += (char) this.tmp;
            in.close();
        } catch(IOException e){
            return false;
        }
        return true;
    }

    private String[] getRecipeData(Presenter p){    // TODO : FIX
        /**
         * String[] response : {<name>, <desc>, <instructions>, <cooktime>, <yield>, <ingredients>}
         * <ingredients> : <name>, <desc>, <amount>, <unit>
         */
        ArrayList<String> ingredients = new ArrayList<String>();
        ArrayList<String> data = new ArrayList<String>();
        if(!this.readData(this.query + GENERAL_INFO_PREFIX)) p.showUser("Failed to retrieve information from server.");
        int st = this.response.indexOf(LABEL_KEYWORD);
        st = this.response.indexOf(QUOTE, st + LABEL_KEYWORD.length()) + 1;
        data.add(this.response.substring(st, this.response.indexOf(QUOTE, st)));
        String desc = "";
        st = this.response.indexOf(CUISINE_KEYWORD);
        st = this.response.indexOf(QUOTE, st + CUISINE_KEYWORD.length()) + 1;
        desc += "cuisine : " + this.response.substring(st, this.response.indexOf(QUOTE, st));
        st = this.response.indexOf(DISH_KEYWORD);
        st = this.response.indexOf(QUOTE, st + DISH_KEYWORD.length()) + 1;
        desc += ", dish : " + this.response.substring(st, this.response.indexOf(QUOTE, st));
        st = this.response.indexOf(MEAL_KEYWORD);
        st = this.response.indexOf(QUOTE, st + MEAL_KEYWORD.length()) + 1;
        desc += ", meal : " + this.response.substring(st, this.response.indexOf(QUOTE, st)) + ".";
        data.add(desc);
        st = this.response.indexOf(URL_KEYWORD);
        st = this.response.indexOf(QUOTE, st + URL_KEYWORD.length()) + 1;
        data.add(this.response.substring(st, this.response.indexOf(QUOTE, st)));
        st = this.response.indexOf(TIME_KEYWORD);
        st = this.response.indexOf(COLON, st + TIME_KEYWORD.length()) + 1;
        data.add(this.response.substring(st, this.response.indexOf(COMMA, st)));
        st = this.response.indexOf(YEILD_KEYWORD);
        st = this.response.indexOf(COLON, st + YEILD_KEYWORD.length()) + 1;
        data.add(this.response.substring(st, this.response.indexOf(COMMA, st)));
        if(!this.readData(this.query + INGREDIENTS_PREFIX)) p.showUser("Failed to retrieve information from server.");
        
        // fix below 
        int index  = -1;

        while ((index = this.response.indexOf("\"quantity\"", index + 1)) >= 0) {
            // do work here
            int qi = index + 11;
            int col = this.response.indexOf(":", qi);
            int end = this.response.indexOf(",", col + 1);
            String quantity = this.response.substring(col + 1, end).trim();
            ingredients.add(quantity);
        }
        while ((index = this.response.indexOf("\"measure\"", index + 1)) >= 0) {
            // do work here
            int mi = index + 10;
            int col = this.response.indexOf("\"", mi);
            int end = this.response.indexOf("\"", col + 1);
            String measure = this.response.substring(col + 1, end).trim();
            ingredients.add(measure);
        }
        while ((index = this.response.indexOf("\"food\"", index + 1)) >= 0) {
            // do work here
            int fi = index + 7;
            int col = this.response.indexOf("\"", fi);
            int end = this.response.indexOf("\"", col + 1);
            String food = this.response.substring(col + 1, end).trim();
            ingredients.add(food);
        }
        int arrayLength = ingredients.size();
        int jumpSize = arrayLength / 3;
        String[] newArr = new String[arrayLength];
        int i1 = 0, i2 = jumpSize, i3 = 2 * jumpSize, i4 = 0;
        while (i3 < arrayLength) {
            newArr[i4] = ingredients.get(i1);
            newArr[i4 + 1] = ingredients.get(i2);
            newArr[i4 + 2] = ingredients.get(i3);
            i1++;
            i2++;
            i3++;
            i4 += 3;
        }
        return newArr;
    }
}