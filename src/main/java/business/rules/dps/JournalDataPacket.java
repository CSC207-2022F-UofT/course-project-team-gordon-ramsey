package business.rules.dps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Journal;
import entities.Entry;
import entities.Recipe;
import entities.RecipeEntry;
import entities.SearchEntry;

public class JournalDataPacket implements Serializable{
    public static Journal parse(JournalDataPacket jdp){
        List<Entry> entries = new ArrayList<Entry>();
        List<Recipe> favorites = new ArrayList<Recipe>();
        for(EntryDataPacket edp : jdp.entries_dp){
            entries.add(EntryDataPacket.parse(edp));
        }
        for(RecipeDataPacket rdp : jdp.favorites_dp){
            favorites.add(RecipeDataPacket.parse(rdp));
        }
        return new Journal(entries, favorites);
    }

    public List<EntryDataPacket> entries_dp;
    public List<RecipeDataPacket> favorites_dp;
    
    public JournalDataPacket(Journal j){
        List<Entry> entries = j.getEntriesList();
        List<Recipe> favorites = j.getFavoritesList();
        this.entries_dp = new ArrayList<EntryDataPacket>();
        this.favorites_dp = new ArrayList<RecipeDataPacket>();
        for(Entry e : entries){
            if(e instanceof SearchEntry) entries_dp.add(new SearchEntryDataPacket((SearchEntry)e));
            if(e instanceof RecipeEntry) entries_dp.add(new RecipeEntryDataPacket((RecipeEntry)e));
        }
        for(Recipe r : favorites){
            this.favorites_dp.add(new RecipeDataPacket(r));
        }
    }
}
