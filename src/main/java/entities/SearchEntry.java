package entities;

import java.time.Instant;

public class SearchEntry extends Entry {
    public String keyword;
    private String[][] collection;

    /**
     *
     * @param time time of search
     * @param keyword keyword string to search
     */
    public SearchEntry(Instant time, String keyword) {
        super(time);
        this.keyword = keyword;
    }

    public String getSearchKeyword(){
        return this.keyword;
    }

    public String[][] getCollection(){
        this.collection = new String[2][2];
        this.collection[0][0] = "Keyword Search Time";
        this.collection[1][0] = "Keywords Searched";
        this.collection[0][1] = this.time.toString();
        this.collection[1][1] = this.keyword;
        return this.collection;
    }
}
