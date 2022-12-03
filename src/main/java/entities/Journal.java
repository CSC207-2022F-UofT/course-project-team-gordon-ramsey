package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a journal of entries.
 */
public class Journal implements Serializable{
    private Entry[] entries;
    private Recipe[] favorites;

    public Journal(Entry[] entries, Recipe[] favorites){
        this.entries = entries;
        this.favorites = favorites;
    }

    public Journal(Entry[] entries){
        this.entries = entries;
        this.favorites = null;
    }

    public void sortEntriesChronologically(){
        Entry.sortChronologically(this.entries);
    }

    public Entry[] getEntries(){
        return this.entries;
    }

    public boolean addEntry(Entry e) {
        ArrayList<Entry> entriesTemp = new ArrayList<Entry>(Arrays.asList(entries));
        entriesTemp.add(e);
        this.entries = (Entry[]) entriesTemp.toArray();
        return true;
    }

    public boolean addFavourite(Recipe r){
        ArrayList<Recipe> favouritesTemp = new ArrayList<Recipe>(Arrays.asList(favorites));
        favouritesTemp.add(r);
        this.favorites = (Recipe[]) favouritesTemp.toArray();
        return true;
    }

    public Recipe[] getFavorites(){
        return this.favorites;
    }
}