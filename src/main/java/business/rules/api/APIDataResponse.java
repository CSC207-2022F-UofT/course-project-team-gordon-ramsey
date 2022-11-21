package business.rules.api;

public class APIDataResponse extends APIResponse{
    public String[][] data;

    public APIDataResponse(String[][] data){
        this.data = data;
    }
}