package business.rules.api;

public class APIRequest{
    public static int UID_ALLOCATOR = 0;

    public int UID;
    public String keyword;
    public int skip;
    public int size_atleast;

    public APIRequest(String keyword, int skip, int size_atleast){
        this.UID = UID_ALLOCATOR++;
        this.keyword = keyword;
        this.skip = skip;
        this.size_atleast = size_atleast;
    }
}
