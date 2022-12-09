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

/**
 * Contains tests for the methods of the Journal entity, which can be found in the entities subfolder of the main
 * folder
 */
public class JournalTest extends TestCase {

    /**
     * Setup phase which runs before every test method
     */
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Teardown phase which runs after every test method
     */
    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests whether the sortEntriesChronologically() method of the Journal entity works as desired
     */
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

    /**
     * Tests whether the getEntries() method of the Journal entity works as desired
     */
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

    /**
     * Tests whether the getFavourites() method of the Journal entity works as desired
     */
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