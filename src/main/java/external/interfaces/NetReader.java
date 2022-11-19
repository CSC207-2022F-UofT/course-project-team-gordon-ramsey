package external.interfaces;

import business.rules.Presenter;
import business.rules.api.APIReader;
import business.rules.api.APIRequest;
import business.rules.api.APIResponse;
import entities.Recipe;

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
                if(this.next == null) return new APIResponse(this.links, query.type);
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
                 System.err.println("Failed to retrieve information from server."); // TODO: call Presenter to show messages !!
            }
            return new APIResponse(this.links, query.type);
        }
        else if(query.type == REQUEST_TYPE.RECIPE){
            this.query = query.data;
            try{
                return new APIResponse(getRecipe(new URL(this.query).openConnection().getInputStream()), query.type);
            }catch(Exception e){
                 System.err.println("Failed to retrieve information from server."); // TODO: call Presenter to show messages !!
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

    private Recipe getRecipe(InputStream stream) throws IOException{
        this.response = "";
        while((this.tmp = stream.read()) != -1) this.response += (char)this.tmp;
        stream.close();
        // Cole's recipe loader here
        return new Recipe(this.response, null, null);  // dummy return for testing
    }
}