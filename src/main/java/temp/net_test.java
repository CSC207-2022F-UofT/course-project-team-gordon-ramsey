package temp;

import business.rules.APIReader;
import business.rules.APIRequest;
import external.interfaces.NetReader;

public class net_test {
    public static void main(String[] args) {
        APIReader api = new NetReader();
        api.request(new APIRequest("chicken"), null);
        
    }
}
