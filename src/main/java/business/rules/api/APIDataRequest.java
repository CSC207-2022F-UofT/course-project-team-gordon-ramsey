package business.rules.api;

import java.util.List;

public class APIDataRequest extends APIRequest {
    public List<String> links;

    public APIDataRequest(List<String> links){
        this.links = links;
    }
}