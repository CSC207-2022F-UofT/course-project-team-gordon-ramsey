package entities;

import junit.framework.TestCase;

import java.time.Duration;

public class RecipeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }


    public void testGetNameGivenAllParameters() {
        Quantity quantity = new Quantity(100, "ML");
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        assertEquals(r.getName(), "Test recipe");
    }

//    public void testGetNameNotGivenDescription() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Duration duration = Duration.ofMinutes(60);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction, duration);
//
//        assertEquals(r.getName(), "Test recipe");
//    }
//
//    public void testGetNameNotGivenCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction);
//
//        assertEquals(r.getName(), "Test recipe");
//    }
//
//    public void testGetNameNotGivenDescriptionAndCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction);
//
//        assertEquals(r.getName(), "Test recipe");
//    }

    public void testGetDescriptionGivenAllParameters() {
        Quantity quantity = new Quantity(100, "ML");
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        assertEquals(r.getDescription(), "This is a test");
    }

//    public void testGetDescriptionNotGivenDescription() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Duration duration = Duration.ofMinutes(60);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction, duration);
//
//        assertEquals(r.getDescription(), "");
//    }
//
//    public void testGetDescriptionNotGivenCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction);
//
//        assertEquals(r.getDescription(), "This is a test");
//    }
//
//    public void testGetDescriptionNotGivenDescriptionOrAndCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction);
//
//        assertEquals(r.getDescription(), "");
//    }

    public void testGetIngredientsGivenAllParameters() {
        Quantity quantity = new Quantity(100, "ML");
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        assertTrue(r.getIngredients() == (ingredient));
    }

//    public void testGetIngredientsNotGivenDescription() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Duration duration = Duration.ofMinutes(60);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction, duration);
//
//        assertTrue(r.getIngredients() == (ingredient));
//    }
//
//    public void testGetIngredientsNotGivenCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction);
//
//        assertTrue(r.getIngredients() == (ingredient));
//    }
//
//    public void testGetIngredientsNotGivenDescriptionAndCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction);
//
//        assertTrue(r.getIngredients() == (ingredient));
//    }

    public void testGetInstructionsGivenAllParameters() {
        Quantity quantity = new Quantity(100, "ML");
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        assertEquals(r.getInstruction(),"");
    }

//    public void testGetInstructionsNotGivenDescription() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Duration duration = Duration.ofMinutes(60);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction, duration);
//
//        assertTrue(r.getInstructions() == (instruction));
//    }
//
//    public void testGetInstructionsNotGivenCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction);
//
//        assertTrue(r.getInstructions() == (instruction));
//    }
//
//    public void testGetInstructionsNotGivenDescriptionAndCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction);
//
//        assertTrue(r.getInstructions() == (instruction));
//    }

    public void testGetCookTimeGivenAllParameters() {
        Quantity quantity = new Quantity(100, "ML");
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        assertTrue(r.getCookTime().getSeconds() - 3600 == 0);
    }

//    public void testGetCookTimeNotGivenDescription() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Duration duration = Duration.ofMinutes(60);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction, duration);
//
//        assertTrue(r.getCookTime().getSeconds() - duration.getSeconds() == 0);
//    }
//
//    public void testGetCookTimeNotGivenCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction);
//
//        assertTrue(r.getCookTime().getSeconds() == 0);
//    }
//
//    public void testGetCookTimeNotGivenDescriptionAndCookTime() {
//        Quantity quantity = new Quantity(100, Quantity.UNIT.ML);
//        Ingredient[] ingredient = new Ingredient[1];
//        ingredient[0] = new Ingredient("water",
//                "agua", quantity);
//        Instruction[] instruction =new Instruction[1];
//        instruction[0] =  new Instruction("Drink the water", ingredient);
//        Recipe r = new Recipe("Test recipe", ingredient, instruction);
//
//        assertTrue(r.getCookTime().getSeconds() == 0);
//    }

    public void testGetYieldGivenAllParameters() {
        Quantity quantity = new Quantity(100, "ML";
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        assertTrue(r.getYield() == 0);
    }

    public void testGetCollection(){

    }

    public void tearDown() throws Exception {
    }


}