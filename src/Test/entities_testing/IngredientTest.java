import entities.Quantity;
import entities.Ingredient;

import junit.framework.TestCase;

/**
 * Contains tests for the methods of the Ingredient entity, which can be found in the entities subfolder of the main
 * folder
 */
public class IngredientTest extends TestCase {
    /**
     * Setup phase which runs before every test method
     */
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Teardown phase which runs after every test method
     */
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests to check if the getName() method works when all possible parameters are provided for the Ingredients entity
     */
    public void testGetNameGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);

        assertEquals(ing.getName(), "water");
    }

    /**
     * Tests to check if the getName() method works when the "description" parameter is not provided when creating
     * an instance of the Ingredient entity
     */
    public void testGetNameNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);

        assertEquals(ing.getName(), "water");
    }

    /**
     * Tests to check if the getDescription() method works when all parameters are provided for the Ingredient entity
     */
    public void testGetDescriptionGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);

        assertEquals(ing.getDescription(), "agua");
    }

    /**
     * Tests to check if the getDescription() method works when the "description" parameter is not provided when
     * creating an instance of the Ingredient entity
     */
    public void testGetDescriptionNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);

        assertEquals(ing.getDescription(), "100 ML Water");
    }

    /**
     * Tests to check if the getQuantity() method works when all parameters are provided for the Ingredient entity
     */
    public void testGetQuantityGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);

        assertEquals(ing.getQuantity(), quantity);
    }

    /**
     * Tests to check if the getQuantity() method works when the "description" parameter is not provided when
     * creating an instance of the Ingredient entity
     */
    public void testGetQuantityNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);

        assertEquals(ing.getQuantity(), quantity);
    }

    /**
     * Tests to check if the setQuantity() method works when all parameters are provided for the Ingredient entity
     */
    public void testSetQuantityGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);
        Quantity new_quantity = new Quantity(500, "ML");

        ing.setQuantity(new_quantity);
        assertEquals(ing.getQuantity(), new_quantity);

    }

    /**
     * Tests to check if the setQuantity() method works when the "description" parameter is not provided when
     * creating an instance of the Ingredient entity
     */
    public void testSetQuantityNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);
        Quantity new_quantity = new Quantity(500, "ML");

        ing.setQuantity(new_quantity);
        assertEquals(ing.getQuantity(), new_quantity);

    }


}


