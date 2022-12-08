package entities;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.Instant;
import java.io.Serializable;

/**
 * Represents an entry of a journal.
 */
public abstract class Entry implements Serializable{

    private final static Comparator<Entry> chrono_compare = new Comparator<Entry>(){
        public int compare(Entry one, Entry two){
            return two.time.compareTo(one.time);
        }
    };

    protected Instant time;

    public Entry(Instant time){
        this.time = time;
    }

    public abstract String[][] getCollection();

    public Instant getTime(){
        return this.time;
    }

    public static List<Entry> sortChronologically(List<Entry> entries){
        Entry[] entries_array = (Entry[]) entries.toArray();
        Arrays.sort(entries_array, chrono_compare);
        return new ArrayList<Entry>(Arrays.asList(entries_array));
    }
}