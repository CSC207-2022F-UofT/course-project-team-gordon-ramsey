package business.rules.api;

import business.rules.Presenter;

public interface APIReader{
    public APILinkResponse request(APILinkRequest query, Presenter p);
    public APIDataResponse request(APIDataRequest query, Presenter p);
    String APP_ID = "45a8cbca";
    String APP_KEY = "4343d22a0cc52431a6d07ee2d73a5c46";
    String HOME = "https://api.edamam.com/api/recipes/v2";
    String SEARCH_PREFIX = HOME + "?type=public&app_id=" + APP_ID + "&app_key=" + APP_KEY;
    String PLUS = "%2B";
    String AND = "&";
    String HTTPS = "https://";
    String QUOTE = "\"";
    String COMMA = ",";
    String COLON = ":";
    String KEYWORD_PREFIX = AND + "q=";
    String NO_INFO_FEILD_PREFIX = AND + "field=null";
    String INGREDIENTS_PREFIX = AND + "field=ingredients";
    String LABEL_PREFIX = AND + "field=label";
    String TIME_PREFIX = AND + "field=totalTime";
    String YIELD_PREFIX = AND + "field=yield";
    String CUISINE_PREFIX = AND + "field=cuisineType";
    String MEAL_PREFIX = AND + "field=mealType";
    String DISH_PREFIX = AND + "field=dishType";
    String SOURCE_LINK_PREFIX = AND + "field=url";
    String GENERAL_INFO_PREFIX = LABEL_PREFIX + TIME_PREFIX + YIELD_PREFIX + CUISINE_PREFIX + MEAL_PREFIX + DISH_PREFIX + SOURCE_LINK_PREFIX;
    String NEXT_KEYWORD = "\"next\"";
    String LABEL_KEYWORD = "\"label\"";
    String TIME_KEYWORD = "\"totalTime\"";
    String YEILD_KEYWORD = "\"yield\"";
    String CUISINE_KEYWORD = "\"cuisineType\"";
    String MEAL_KEYWORD = "\"mealType\"";
    String DISH_KEYWORD = "\"dishType\"";
    String URL_KEYWORD = "\"url\"";
    String TEXT_KEYWORD = "\"text\"";
    String QUANTITY_KEYWORD = "\"quantity\"";
    String MEASURE_KEYWORD = "\"measure\"";
    String FOOD_KEYWORD = "\"food\"";

    String TIME_PREFIX = AND + "field=totalTime";

    String INSTRUCTION_LINK_PREFIX = AND + "field=url";
}
