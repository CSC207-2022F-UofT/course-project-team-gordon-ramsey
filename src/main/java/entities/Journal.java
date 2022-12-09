package entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Represents a journal of entries.
 */
public class Journal implements Serializable{
    private List<Entry> entries;
    private List<Recipe> favorites;

    /**
     *
     * @param entries entries to be included in the journal
     * @param favorites recipes to be saved as favorites
     */
    public Journal(Entry[] entries, Recipe[] favorites){
        this.entries = new ArrayList<Entry>(Arrays.asList(entries));
        this.favorites = new ArrayList<Recipe>(Arrays.asList(favorites));
    }

    /**
     *
     * @param entries entries to be included in the journal
     */
    public Journal(Entry[] entries){
        this.entries = new ArrayList<Entry>(Arrays.asList(entries));
        this.favorites = new ArrayList<Recipe>();
    }

    /**
     *
     * @param entries entries to be included in the journal
     * @param favorites recipes to be saved as favorites
     */
    public Journal(List<Entry> entries, List<Recipe> favorites){
        this.entries = entries;
        this.favorites = favorites;
    }

    /**
     *
     * @param entries entries to be included in the journal
     */
    public Journal(List<Entry> entries){
        this.entries = entries;
    }

    public Journal(){
        this.entries = new ArrayList<Entry>();
        this.favorites = new ArrayList<Recipe>();
    }

    /**
     * Sorts the journal entries in chronological order.
     */
    public void sortEntriesChronologically(){
        Entry.sortChronologically(this.entries);
    }

    public Entry[] getEntries(){
        return (Entry[]) this.entries.toArray();
    }

    /**
     * Adds a new entry to the journal.
     * @param e Entry object to be added to the journal
     * @return returns true to indicate successful addition
     */
    public List<Entry> getEntriesList(){
        return this.entries;
    }

    public boolean addEntry(Entry e) {
        this.entries.add(e);
        return true;
    }

    /**
     * Adds a given recipe to the list of favorite recipes.
     * @param r a Recipe object to be added to favorites
     * @return returns true to indicate successful addition
     */
    public boolean addFavourite(Recipe r){
        this.favorites.add(r);
        this.addEntry(new RecipeEntry(Instant.now(), r));
        return true;
    }

    public Recipe[] getFavorites(){
        return (Recipe[]) this.favorites.toArray();
    }

    public List<Recipe> getFavoritesList(){
        return this.favorites;
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
