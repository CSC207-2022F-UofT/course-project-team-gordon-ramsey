package business.rules.api;

import business.rules.api.APIReader.REQUEST_TYPE;

public class APIResponse{
    public Object data;
    public REQUEST_TYPE type;

    public APIResponse(Object data, REQUEST_TYPE type){
        this.data = data;
        this.type = type;
    }
}