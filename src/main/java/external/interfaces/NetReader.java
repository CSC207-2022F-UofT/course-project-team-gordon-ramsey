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

    @Override
    public APIResponse request(APIRequest query, Presenter p) {
        /**
         * PRECONDITION: query.data only alphanumeric if keyword request.
         * POSTCONDITION: links, next, last_request_UID remain untouched if recipe
         * request.
         */
        if (query.type == REQUEST_TYPE.KEYWORD) {
            if (last_request_UID == query.UID) {
                if (this.next == null)
                    return new APILinkResponse(this.links);
                this.query = this.next;
            } else {
                this.query = SEARCH_PREFIX + NO_INFO_FEILD_PREFIX + KEYWORD_PREFIX
                        + query.data.trim().replaceAll(" ", PLUS);
                this.last_request_UID = query.UID;
                this.links = new ArrayList<String>();
            }
            try {
                this.readData(this.query);
                this.loadLinks();
            } catch (Exception e) {
                p.showUser("Failed to retrieve information from server.");
            }
            return new APILinkResponse(this.links);
        } else if (query.type == REQUEST_TYPE.RECIPE) {
            this.query = query.data;
            try {
                return new APIDataResponse(getRecipeData(this.query));
            } catch (Exception e) {
                p.showUser("Failed to retrieve information from server.");
            }
        }
        return null;
    }

    private void readData(String link) throws IOException {
        /**
         * loads page on this.response
         */
        in = new URL(link).openConnection().getInputStream();
        this.response = "";
        while ((this.tmp = in.read()) != -1)
            this.response += (char) this.tmp;
        in.close();
    }

    private void loadLinks() throws IOException {
        /**
         * PRECONDITON: this.links, this.response initilaized
         */
        if (this.response.indexOf(NEXT_KEYWORD) > 0) {
            this.tmp = this.response.indexOf(HTTPS);
            this.next = this.response.substring(this.tmp, this.response.indexOf(QUOTE, this.tmp + 1));
        } else {
            this.tmp = -1;
            this.next = null;
        }
        while ((this.tmp = this.response.indexOf(HTTPS, this.tmp + 1)) != -1) {
            this.links.add(this.response.substring(this.tmp, this.response.indexOf(QUOTE, this.tmp + 1)));
        }
    }

    private String[] getRecipeData(String recipe_link) throws IOException {
        /**
         * String[] response : {<name>, <desc>, <ingredients>, <instructions>, <cook
         * time>}
         * <ingredients> : <name>:<amount>:<unit><delimiter>
         */
        this.readData(recipe_link + INGREDIENTS_PREFIX);
        ArrayList<String> ingredients = new ArrayList<String>();
        int index = -1;
        while ((index = this.response.indexOf("\"quantity\"", index + 1)) >= 0) {
            // do work here
            int qi = index + 10;
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

    public String getCookTime(String recipeLink) throws IOException {
        this.readData(recipeLink + TIME_PREFIX);
        int index = this.response.indexOf("\"totalTime\"");
        int col = index+11;
        int end = this.response.indexOf("}", col);
        String cookingTime = this.response.substring(col+1,end).trim();
        return cookingTime;
    }

    public String getInstructions(String recipeLink) throws IOException {
        this.readData(recipeLink + INSTRUCTION_LINK_PREFIX);
        int index = this.response.indexOf("\"url\"");
        int col = index+5;
        int end = this.response.indexOf("\"", col+2);
        String instructionLink = this.response.substring(col+2,end).trim();
        return instructionLink;
    }



}