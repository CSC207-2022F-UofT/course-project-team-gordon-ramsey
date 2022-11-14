package business.rules;

import entities.*;

public class APIResponse{
    public Recipe[] data;

    public APIResponse(Recipe[] response){
        this.data = response;
    }
}