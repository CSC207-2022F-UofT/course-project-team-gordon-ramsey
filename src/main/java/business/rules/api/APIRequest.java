package business.rules.api;

import business.rules.api.APIReader.REQUEST_TYPE;

public class APIRequest{
    public static int UID_ALLOCATOR = 0;

    public String data;
    public REQUEST_TYPE type;
    public int UID;

    public APIRequest(String data, REQUEST_TYPE type){
        this.data = data;
        this.type = type;
        this.UID = UID_ALLOCATOR++;
    }
}
