package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Represents a journal of entries.
 */
public class Journal implements Serializable{
    private List<Entry> entries;
    private List<Recipe> favorites;

    public Journal(Entry[] entries, Recipe[] favorites){
        this.entries = new ArrayList<Entry>(Arrays.asList(entries));
        this.favorites = new ArrayList<Recipe>(Arrays.asList(favorites));
    }

    public Journal(Entry[] entries){
        this.entries = new ArrayList<Entry>(Arrays.asList(entries));
        this.favorites = new ArrayList<Recipe>();
    }

    public Journal(){
        this.entries = new ArrayList<Entry>();
        this.favorites = new ArrayList<Recipe>();
    }

    public void sortEntriesChronologically(){
        Entry.sortChronologically(this.entries);
    }

    public Entry[] getEntries(){
        return (Entry[]) this.entries.toArray();
    }

    public boolean addEntry(Entry e) {
        this.entries.add(e);
        return true;
    }

    public boolean addFavourite(Recipe r){
        this.favorites.add(r);
        return true;
    }

    public Recipe[] getFavorites(){
        return (Recipe[]) this.favorites.toArray();
    }

    public String[][][] getCollection(){
        String[][][] collection = new String[this.entries.size()][][];
        for(int i = 0; i < collection.length; i++){
            collection[i] = this.entries.get(i).getCollection();
        }
        return collection;
    }

    public String[][][] getFavoritesCollection(){
        String[][][] collection = new String[this.favorites.size()][][];
        for(int i = 0; i < collection.length; i++){
            collection[i] = this.favorites.get(i).getCollection();
        }
        return collection;
    }
}