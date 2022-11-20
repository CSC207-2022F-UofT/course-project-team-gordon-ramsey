package business.rules.api;

import business.rules.api.APIReader.REQUEST_TYPE;

public class APIResponse{
    public REQUEST_TYPE type;

    public APIResponse(REQUEST_TYPE type){
        this.type = type;
    }
}