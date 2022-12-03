package entities;

import java.time.Instant;

public class SearchEntry extends Entry {

    private String keyword;

    protected SearchEntry(Instant time, String keyword) {
        super(time);
        this.keyword = keyword;
    }
}
