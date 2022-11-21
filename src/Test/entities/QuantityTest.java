package entities;

import junit.framework.TestCase;

public class QuantityTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetAmount(){
        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
        assertEquals(quantity.getAmount(), 100);
    }

    public void testGetUnit(){
        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
        assertEquals(quantity.getUnit(), Quantity.UNIT.ML);
    }

    // Do I have to test if all the interconversions between units work?

}