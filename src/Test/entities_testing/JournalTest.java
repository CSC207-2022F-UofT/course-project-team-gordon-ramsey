import entities.RecipeEntry;
import entities.Journal;
import entities.Entry;
import entities.Recipe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

public class JournalTest extends TestCase {

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
        RecipeEntry entry1 = new RecipeEntry(Instant.now(),null);
        RecipeEntry entry2 = new RecipeEntry(Instant.now(), null);
        RecipeEntry entry3 = new RecipeEntry(Instant.now(), null);
        RecipeEntry entry4 = new RecipeEntry(Instant.now(), null);
        RecipeEntry[] array1 = {entry3, entry2, entry1, entry4};
        RecipeEntry[] array_expected = {entry1, entry2, entry3, entry4};
        Journal journal = new Journal(array1);
        journal.sortEntriesChronologically();
        assertEquals(journal.getEntries(), array_expected);
    }


    @Test
    void getEntries() {
        RecipeEntry entry1 = new RecipeEntry(Instant.now(),null);
        RecipeEntry entry2 = new RecipeEntry(Instant.now(), null);
        RecipeEntry entry3 = new RecipeEntry(Instant.now(), null);
        RecipeEntry entry4 = new RecipeEntry(Instant.now(), null);
        RecipeEntry[] array = {entry2, entry4, entry3, entry1};
        Journal journal = new Journal(array);
        assertEquals(journal.getEntries(), array);
    }

    @Test
    void getFavorites() {
        RecipeEntry entry1 = new RecipeEntry(Instant.now(), null);
        RecipeEntry entry2 = new RecipeEntry(Instant.now(), null);
        RecipeEntry entry3 = new RecipeEntry(Instant.now(), null);
        RecipeEntry entry4 = new RecipeEntry(Instant.now(), null);
        RecipeEntry[] array1 = {entry2, entry4, entry3};
        Recipe[] array_favourites = {entry1.recipe};
        Journal journal = new Journal(array1, array_favourites);
        assertEquals(journal.getFavorites(), array_favourites);
        assertEquals(journal.getEntries(), array1);
    }
}