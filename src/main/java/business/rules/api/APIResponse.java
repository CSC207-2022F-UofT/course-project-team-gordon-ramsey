package business.rules.api;

/**
 * API response string from NetReader
 */
public class APIResponse{
    public String[][] data;

    /**
     *
     * @param data the String[][] response data
     */
    public APIResponse(String[][] data){
        this.data = data;
    }
}