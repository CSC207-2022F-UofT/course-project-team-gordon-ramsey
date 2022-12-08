import entities.Quantity;

import junit.framework.TestCase;

/**
 * Contains tests for the methods of the Quantity entity, which can be found in the entities subfolder of the main
 * folder
 */
public class QuantityTest extends TestCase {

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
    }

    /**
     * Tests whether the getAmount() method of the Quantity entity works as desired
     */
    public void testGetAmount(){
        Quantity quantity = new Quantity(100, "ML");
        assertEquals(quantity.getAmount(), 100);
    }

    /**
     * Tests whether the getUnit() method of the Quantity entity works as desired
     */
    public void testGetUnit(){
        Quantity quantity = new Quantity(100, "ML");
        assertEquals(quantity.getUnit(), "ML");
    }

    /**
     * Tests whether the getAmount() method of the Quantity entity works as desired after the original quantity is
     * scaled using the scale() function
     */
    public void testGetAmountAfterScaling(){
        Quantity quantity = new Quantity(100, "ML");
        quantity = Quantity.scale(quantity, 5);
        assertEquals(quantity.getAmount(), 500);

    }

    /**
     * Tests whether the getUnit() method of the Quantity entity works as desired after the original quantity is
     * scaled using the scale() function
     */
    public void testGetUnitAfterScaling(){
        Quantity quantity = new Quantity(100, "ML");
        quantity = Quantity.scale(quantity, 5);
        assertEquals(quantity.getUnit(), "ML");

    }

    /**
     * Tests whether the add() method of the Quantity entity works as desired
     */
    public void testAdd(){
        Quantity quantity = new Quantity(100, "ML");
        quantity.add(400);
        assertEquals(quantity.getAmount(), 500);

    }

}
