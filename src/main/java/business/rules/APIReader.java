package business.rules;

public interface APIReader{
    public APIResponse request(APIRequest query, Presenter p);
    String APP_ID = "45a8cbca";
    String APP_KEY = "4343d22a0cc52431a6d07ee2d73a5c46";
    String HOME = "https://api.edamam.com/api/recipes/v2";
    String SEARCH_PREFIX = HOME + "?type=public&app_id=" + APP_ID + "&app_key=" + APP_KEY;
    String PLUS = "%2B";
}
