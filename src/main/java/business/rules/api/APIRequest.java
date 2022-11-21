package business.rules.api;

public class APIRequest{
    public static int UID_ALLOCATOR = 0;

    public int UID;

    public APIRequest(){
        this.UID = UID_ALLOCATOR++;
    }
}
