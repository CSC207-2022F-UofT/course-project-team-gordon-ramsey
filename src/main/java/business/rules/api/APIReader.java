package business.rules.api;

import business.rules.Presenter;

public interface APIReader{
    public static enum REQUEST_TYPE{
        KEYWORD,
        RECIPE
    }
    public APIResponse request(APIRequest query, Presenter p);
    String APP_ID = "45a8cbca";
    String APP_KEY = "4343d22a0cc52431a6d07ee2d73a5c46";
    String HOME = "https://api.edamam.com/api/recipes/v2";
    String SEARCH_PREFIX = HOME + "?type=public&app_id=" + APP_ID + "&app_key=" + APP_KEY;
    String PLUS = "%2B";
    String AND = "&";
    String HTTPS = "https://";
    String QUOTE="\"";
    String KEYWORD_PREFIX = AND + "q=";
    String NO_INFO_FEILD_PREFIX = AND + "field=null";
    String INGREDIENTS_PREFIX = AND + "field=ingredients";
    String NEXT_KEYWORD = "\"next\"";

    String TIME_PREFIX = AND + "field=totalTime";

    String INSTRUCTION_LINK_PREFIX = AND + "field=url";
}
