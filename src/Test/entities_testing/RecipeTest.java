import entities.Quantity;
import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import junit.framework.TestCase;

import java.time.Duration;

public class RecipeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown()throws Exception {
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

    public void testGetYieldGivenAllParameters() {
        Quantity quantity = new Quantity(100, "ML");
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        assertTrue(r.getYield() == 0);
    }

    public void testGetCollection(){
        Quantity quantity = new Quantity(100, "ML");
        Ingredient[] ingredient = new Ingredient[1];
        ingredient[0] = new Ingredient("water",
                "agua", quantity);
        Instruction instruction =  new Instruction("");
        Duration duration = Duration.ofMinutes(60);
        Recipe r = new Recipe("Test recipe", "This is a test", ingredient, instruction, duration, 0);

        String[][] collection = {{"Name", "Test recipe"},
                {"Description", "This is a test"},
                {"Ingredients", "aqua"},
                {"Instructions", ""},
                {"Cooking Time", "60 minutes"},
                {"Yield", "0"}};

        assertEquals(r.getCollection(), collection);
    }
}