package business.rules.api;

import business.rules.api.APIReader.REQUEST_TYPE;

public class APIDataResponse extends APIResponse{
    public Object[] data;

    public APIDataResponse(Object[] data){
        super(REQUEST_TYPE.RECIPE);
        this.data = data;
    }
}
