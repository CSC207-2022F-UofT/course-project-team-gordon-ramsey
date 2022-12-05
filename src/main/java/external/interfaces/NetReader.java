package external.interfaces;

import business.rules.Presenter;
import business.rules.api.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.IOException;

public class NetReader implements APIReader {
    private String response, query, next;
    private List<String[]> recipe_data;
    private int last_request_UID = -1;
    private int REQUESTS_AVAIALABLE = 10;
    private int SECOND = 1000;
    private Thread REQUEST_LIMIT_TRACKER, PROGRESS_DISPLAY;
    private Runnable PROGRESS_DISPLAY_RUNNABLE;
    private Presenter presenter;

    public NetReader(Presenter p){
        this.next = "";
        this.presenter = p;
        this.REQUEST_LIMIT_TRACKER = new Thread(new Runnable(){
            public void run(){
                while(true){
                    try{Thread.sleep(60 * SECOND);}
                    catch(InterruptedException e){
                        presenter.showUser("Closing API Request Tracker Thread.");
                        break;
                    }
                    REQUESTS_AVAIALABLE = 10;
                }
            }
        });
        this.PROGRESS_DISPLAY_RUNNABLE = new Runnable(){
            public void run(){
                while(true){
                    try{Thread.sleep(2 * SECOND);}
                    catch(InterruptedException e){ break; }
                    presenter.showUser("Recipes loaded : " + recipe_data.size() + ", Server requests available : " + REQUESTS_AVAIALABLE);
                }
            }
        };
        this.REQUEST_LIMIT_TRACKER.start();
    }

    public APIResponse request(APIRequest query, boolean verbose){
        /**
         * PRECONDITION: query.keyword only alphanumeric.
         */
        if (last_request_UID == query.UID) {
            if (this.next == null){
                this.PROGRESS_DISPLAY.interrupt();
                return new APIResponse(this.getStoredData());
            }
            this.query = this.next;
        } else {
            this.query = SEARCH_PREFIX + GENERAL_INFO_PREFIX + KEYWORD_PREFIX + query.keyword.trim().replaceAll(" ", PLUS);
            this.last_request_UID = query.UID;
            this.recipe_data = new ArrayList<String[]>();
            this.PROGRESS_DISPLAY = new Thread(PROGRESS_DISPLAY_RUNNABLE);
            this.PROGRESS_DISPLAY.start();
        }
        if(!this.readData(this.query)){
            this.presenter.showUser("Failed to retrieve information from server.");
            this.PROGRESS_DISPLAY.interrupt();
            return new APIResponse(this.getStoredData());
        }
        String[] recipe_info = this.response.split(RECIPE_KEYWORD);
        if (recipe_info[0].indexOf(NEXT_KEYWORD) > 0) {
            int tmp = recipe_info[0].indexOf(HTTPS);
            this.next = recipe_info[0].substring(tmp, recipe_info[0].indexOf(QUOTE, tmp + 1));
        } else {this.next = null;}
        if(query.skip > 0){
            query.skip -= recipe_info.length - 1;
            return this.request(query, verbose);
        }
        for(int i = 1; i < Math.min(recipe_info.length, query.size_atleast + 1); i++){this.recipe_data.add(this.getRecipeData(recipe_info[i]));}
        query.size_atleast -= recipe_info.length - 1;
        if(query.size_atleast > 0) return this.request(query, verbose);
        this.PROGRESS_DISPLAY.interrupt();
        return new APIResponse(this.getStoredData());
    }

    private String[][] getStoredData(){
        String[][] arr = new String[this.recipe_data.size()][];
        for(int i = 0; i < this.recipe_data.size(); i++){
            arr[i] = this.recipe_data.get(i).clone();
        }
        this.recipe_data = null;
        return arr;
    }

    private boolean readData(String link) {
        /**
         * loads page on this.response
         */
        if(this.REQUESTS_AVAIALABLE <= 0){
            this.presenter.showUser("Server request cap reached, try again in a minute.");
            return false;
        }
        this.REQUESTS_AVAIALABLE -= 1;
        try{
            InputStream in = new URL(link).openConnection().getInputStream();
            int tmp = -1;
            this.response = "";
            while ((tmp = in.read()) != -1) this.response += (char) tmp;
            in.close();
        } catch(IOException e){
            return false;
        }
        return true;
    }

    private String[] getRecipeData(String raw){
        /**
         * String[] response : {<name>, <desc>, <instructions>, <cooktime>, <yield>, <ingredients>}
         * <ingredients> : <name>, <desc>, <amount>, <unit>
         */
        ArrayList<String> data = new ArrayList<String>();
        int st = raw.indexOf(LABEL_KEYWORD);
        st = raw.indexOf(QUOTE, st + LABEL_KEYWORD.length()) + 1;
        data.add(raw.substring(st, raw.indexOf(QUOTE, st)));
        String desc = "";
        st = raw.indexOf(CUISINE_KEYWORD);
        st = raw.indexOf(QUOTE, st + CUISINE_KEYWORD.length()) + 1;
        desc += "cuisine : " + raw.substring(st, raw.indexOf(QUOTE, st));
        st = raw.indexOf(DISH_KEYWORD);
        st = raw.indexOf(QUOTE, st + DISH_KEYWORD.length()) + 1;
        desc += ", dish : " + raw.substring(st, raw.indexOf(QUOTE, st));
        st = raw.indexOf(MEAL_KEYWORD);
        st = raw.indexOf(QUOTE, st + MEAL_KEYWORD.length()) + 1;
        desc += ", meal : " + raw.substring(st, raw.indexOf(QUOTE, st)) + ".";
        data.add(desc);
        st = raw.indexOf(URL_KEYWORD);
        st = raw.indexOf(QUOTE, st + URL_KEYWORD.length()) + 1;
        data.add(raw.substring(st, raw.indexOf(QUOTE, st)));
        st = raw.indexOf(TIME_KEYWORD);
        st = raw.indexOf(COLON, st + TIME_KEYWORD.length()) + 1;
        data.add(raw.substring(st, raw.indexOf(COMMA, st)));
        st = raw.indexOf(YEILD_KEYWORD);
        st = raw.indexOf(COLON, st + YEILD_KEYWORD.length()) + 1;
        data.add(raw.substring(st, raw.indexOf(COMMA, st)));
        int ind = -1;
        while((ind = raw.indexOf(TEXT_KEYWORD, ind + 1)) >= 0){
            st = raw.indexOf(QUOTE, ind + TEXT_KEYWORD.length()) + 1;
            data.add(raw.substring(st, raw.indexOf(QUOTE, st)));
            st = raw.indexOf(QUANTITY_KEYWORD, st);
            st = raw.indexOf(COLON, st + QUANTITY_KEYWORD.length()) + 1;
            data.add(raw.substring(st, raw.indexOf(COMMA, st)));
            st = raw.indexOf(MEASURE_KEYWORD, st);
            st = raw.indexOf(QUOTE, st + MEASURE_KEYWORD.length()) + 1;
            data.add(raw.substring(st, raw.indexOf(QUOTE, st)));
            st = raw.indexOf(FOOD_KEYWORD, st);
            st = raw.indexOf(QUOTE, st + FOOD_KEYWORD.length()) + 1;
            data.add(raw.substring(st, raw.indexOf(QUOTE, st)));
        }
        String [] recipes = new String[data.size()];
        for(int i = 0; i < recipes.length; i++){
            recipes[i] = data.get(i);
        }
        return recipes;
    }

    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    public void stopClocks(){
        this.REQUEST_LIMIT_TRACKER.interrupt();
    }
}
