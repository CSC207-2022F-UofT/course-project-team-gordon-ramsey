package business.rules.api;

import java.util.List;

public class APILinkResponse extends APIResponse{
    public List<String> data;

    public APILinkResponse(List<String> data){
        this.data = data;
    }
}
