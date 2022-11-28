package entities;

import java.util.Arrays;
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

    private Instant time;

    protected Entry(Instant time){
        this.time = time;
    }

    public static void sortChronologically(Entry[] entries){
        Arrays.sort(entries, chrono_compare);
    }
}