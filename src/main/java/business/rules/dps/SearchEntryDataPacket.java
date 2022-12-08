package business.rules.dps;

import entities.SearchEntry;

public class SearchEntryDataPacket extends EntryDataPacket{
    public static SearchEntry parse(SearchEntryDataPacket edp){
        return new SearchEntry(edp.time, edp.keyword);
    }

    public String keyword;

    public SearchEntryDataPacket(SearchEntry se){
        super(se);
        this.keyword = se.getSearchKeyword();
    }
}