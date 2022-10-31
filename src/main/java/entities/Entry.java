package main.java.entities;

import java.util.Arrays;
import java.util.Comparator;
import java.time.Instant;
import java.util.Serializable;

/**
 * Represents an entry of a journal.
 */
public abstract class Entry implements Serializable{
    public enum ENTRY_TYPE{
        RECIPE_RECORD,
        SEARCH_RECORD
    }

    private final static Comparator<Entry> chrono_compare = new Comparator<Entry>(){
        public int compare(Entry one, Entry two){
            return two.time.compareTo(one.time);
        }
    };

    private Instant time;
    private ENTRY_TYPE type;

    protected Entry(Instant time, ENTRY_TYPE type){
        this.time = time;
        this.type = type;
    }

    public static void sortChronologically(Entry[] entries){
        Arrays.sort(entries, chrono_compare);
    }
}