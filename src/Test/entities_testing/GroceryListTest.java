import entities.GroceryList;
import entities.Ingredient;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class GroceryListTest extends TestCase {

    @BeforeEach
    public void setUp() throws Exception{
        super.setUp();
    }

    @AfterEach
    public void tearDown() throws Exception{
        super.tearDown();
    }

    @Test
    void getIngredients1() {
        GroceryList groceryList = new GroceryList();
        ArrayList<Ingredient> emptyList = new ArrayList<>();
        assertEquals(groceryList.getIngredients(), emptyList);
    }
}
