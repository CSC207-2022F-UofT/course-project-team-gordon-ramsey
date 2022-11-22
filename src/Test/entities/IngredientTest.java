package entities;

import junit.framework.TestCase;

public class IngredientTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetNameGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);

        assertEquals(ing.getName(), "water");
    }

    public void testGetNameNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);

        assertEquals(ing.getName(), "water");
    }

    public void testGetDescriptionGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);

        assertEquals(ing.getDescription(), "agua");
    }

    public void testGetDescriptionNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);

        assertEquals(ing.getDescription(), "100 ML Water");
    }

    public void testGetQuantityGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);

        assertEquals(ing.getQuantity(), quantity);
    }

    public void testGetQuantityNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);

        assertEquals(ing.getQuantity(), quantity);
    }

    public void testSetQuantityGivenAllParameters(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", "agua", quantity);
        Quantity new_quantity = new Quantity(500, "ML");

        ing.setQuantity(new_quantity);
        assertEquals(ing.getQuantity(), new_quantity);

    }

    public void testSetQuantityNotGivenDescription(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient ing = new Ingredient("water", quantity);
        Quantity new_quantity = new Quantity(500, "ML");

        ing.setQuantity(new_quantity);
        assertEquals(ing.getQuantity(), new_quantity);

    }


}