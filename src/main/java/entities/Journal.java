package entities;

import java.io.Serializable;

/**
 * Represents a journal of entries.
 */
public class Journal implements Serializable{
    private Entry[] entries;
    private Entry[] favorites;

    public Journal(Entry[] entries, Entry[] favorites){
        this.entries = entries;
        this.favorites = favorites;
    }

    public Journal(Entry[] entries){
        this.entries = entries;
        this.favorites = null;
    }

    public Object sortEntriesChronologically(){
        Entry.sortChronologically(this.entries);
        return null;
    }

    public Object sortFavoritesChronologically(){
        Entry.sortChronologically(this.favorites);
        return null;
    }

    public Entry[] getEntries(){
        return this.entries;
    }

    public Entry[] getFavorites(){
        return this.favorites;
    }
}