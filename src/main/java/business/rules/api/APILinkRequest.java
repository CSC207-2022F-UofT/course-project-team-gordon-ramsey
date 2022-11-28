package business.rules.api;

public class APILinkRequest extends APIRequest {
    public String keyword;
    public int skip;

    public APILinkRequest(String keyword, int skip){
        this.keyword = keyword;
        this.skip = skip;
    }
}
