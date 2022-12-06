import entities.Quantity;

import junit.framework.TestCase;

public class QuantityTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetAmount(){
        Quantity quantity = new Quantity(100, "ML");
        assertEquals(quantity.getAmount(), 100);
    }

    public void testGetUnit(){
        Quantity quantity = new Quantity(100, "ML");
        assertEquals(quantity.getUnit(), "ML");
    }

    public void testGetAmountAfterScaling(){
        Quantity quantity = new Quantity(100, "ML");
        quantity = Quantity.scale(quantity, 5);
        assertEquals(quantity.getAmount(), 500);

    }

    public void testGetUnitAfterScaling(){
        Quantity quantity = new Quantity(100, "ML");
        quantity = Quantity.scale(quantity, 5);
        assertEquals(quantity.getUnit(), "ML");

    }

    public void testAdd(){
        Quantity quantity = new Quantity(100, "ML");
        quantity.add(400);
        assertEquals(quantity.getAmount(), 500);

    }

}
