package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest extends TestCase {

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    void sortEntriesChronologically1() {
        Entry entry1 = new Entry(Instant.now(),null);
        Entry entry2 = new Entry(Instant.now(), null);
        Entry entry3 = new Entry(Instant.now(), null);
        Entry entry4 = new Entry(Instant.now(), null);
        Entry[] array1 = {entry3, entry2, entry1, entry4};
        Entry[] array_expected = {entry1, entry2, entry3, entry4};
        Journal journal = new Journal(array1);
        assertEquals(journal.sortEntriesChronologically(), array_expected);
    }

    @Test
    void sortFavoritesChronologically() {
        Entry entry1 = new Entry(Instant.now(),null);
        Entry entry2 = new Entry(Instant.now(), null);
        Entry entry3 = new Entry(Instant.now(), null);
        Entry entry4 = new Entry(Instant.now(), null);
        Entry[] array_favourites = {entry2, entry4, entry1};
        Entry[] array1 = {entry3};
        Entry[] array_expected = {entry1, entry2, entry4};
        Journal journal = new Journal(array1, array_favourites);
        assertEquals(journal.sortFavoritesChronologically(), array_expected);


    }

    @Test
    void getEntries() {
        Entry entry1 = new Entry(Instant.now(),null);
        Entry entry2 = new Entry(Instant.now(), null);
        Entry entry3 = new Entry(Instant.now(), null);
        Entry entry4 = new Entry(Instant.now(), null);
        Entry[] array = {entry2, entry4, entry3, entry1};
        Journal journal = new Journal(array);
        assertEquals(journal.getEntries(), array);
    }

    @Test
    void getFavorites() {
        Entry entry1 = new Entry(Instant.now(),null);
        Entry entry2 = new Entry(Instant.now(), null);
        Entry entry3 = new Entry(Instant.now(), null);
        Entry entry4 = new Entry(Instant.now(), null);
        Entry[] array1 = {entry2, entry4, entry3};
        Entry[] array_favourites = {entry1};
        Journal journal = new Journal(array1, array_favourites);
        assertEquals(journal.getFavorites(), array_favourites);
        assertEquals(journal.getEntries(), array1);
    }
}