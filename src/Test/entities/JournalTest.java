package entities;

import junit.framework.TestCase;
import java.time.Instant;

public class JournalTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetEntries(){
        Instant timestamp = Instant.now();
        Entry[] entry = new Entry[1];
        entry[0] = new Entry(timestamp, Entry.ENTRY_TYPE.SEARCH_RECORD);
)
    }


}
