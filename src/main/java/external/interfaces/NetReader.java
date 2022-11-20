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
        this.response = "";
        while((this.tmp = stream.read()) != -1) this.response += (char)this.tmp;
        stream.close();
        // this.response : data in string.
        // Cole's recipe loader here
        return null;
    }
}