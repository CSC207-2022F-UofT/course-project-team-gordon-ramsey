package business.rules.api;

import java.util.List;

import business.rules.api.APIReader.REQUEST_TYPE;

public class APILinkResponse extends APIResponse{
    public List<String> data;

    public APILinkResponse(List<String> data){
        super(REQUEST_TYPE.KEYWORD);
        this.data = data;
    }
}
