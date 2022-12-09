import entities.GroceryList;
import entities.Ingredient;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


/**
 * Contains tests for the methods of the GroceryList entity, which can be found in the entities subfolder of the main
 * folder
 */
public class GroceryListTest extends TestCase {
    /**
     * Setup phase which runs before every test method
     */
    @BeforeEach
    public void setUp() throws Exception{
        super.setUp();
    }

    /**
     * Teardown phase which runs after every test method
     */
    @AfterEach
    public void tearDown() throws Exception{
        super.tearDown();
    }

    /**
     * Tests to check if the getIngredients() method works when no ingredients are stored
     */
    @Test
    void getIngredients1() {
        GroceryList groceryList = new GroceryList();
        ArrayList<Ingredient> emptyList = new ArrayList<>();
        assertEquals(groceryList.getIngredients(), emptyList);
    }
}
