package external.interfaces;

import business.rules.APIReader;
import business.rules.APIRequest;
import business.rules.APIResponse;
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;

public class NetReader implements APIReader{
    private String response;
    private char character;

    @Override
    public APIResponse request(APIRequest query){
        try{
            loadResponse(new URL(SEARCH_PREFIX).openConnection().getInputStream());
        }catch(Exception e){
         	System.err.println("Failed to retrieve information from server."); // TODO: call Presenter to show messages !!
        }
        return new APIResponse(this.response.split("{[]}"));
    }

    private void loadResponse(InputStream stream) throws IOException{
        this.response = "";
        while( (this.character = stream.read()) != -1) this.response += this.character;
        stream.close();
    }
}