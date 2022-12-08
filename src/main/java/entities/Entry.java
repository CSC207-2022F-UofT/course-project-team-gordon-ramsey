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
        /**
         * Compares two Entry objects according to the time of creation.
         * @param one the first Entry object to be compared.
         * @param two the second Entry object to be compared.
         * @return a positive int if the first entry is created earlier, zero if created at the same time, a negative int if the second entry is created earlier
         */
        public int compare(Entry one, Entry two){
            return two.time.compareTo(one.time);
        }
    };

    protected Instant time;

    public Entry(Instant time){
        this.time = time;
    }

    public abstract String[][] getCollection();

    /**
     * Sorts the list of entries in chronological order.
     * @param entries the list of Entry objects to be sorted chronologically
     * @return the chronologically sorted list
     */
    public static List<Entry> sortChronologically(List<Entry> entries){
        Entry[] entries_array = (Entry[]) entries.toArray();
        Arrays.sort(entries_array, chrono_compare);
        return new ArrayList<Entry>(Arrays.asList(entries_array));
    }
}
