package entities;

import junit.framework.TestCase;

public class InstructionTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetInstruction() {
        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);

        Instruction ins = new Instruction("Drink the water", ingredient);
        assertTrue(ins.getInstruction() == "Drink the water");
    }

    public void testGetIngredients() {
        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);

        Instruction ins = new Instruction("Drink the water", ingredient);
        assertTrue(ins.getIngredients() == ingredient);
    }
}