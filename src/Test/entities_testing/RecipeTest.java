import entities.Quantity;
import entities.Ingredient;
import entities.Instruction;
import entities.Recipe;

import junit.framework.TestCase;

import java.time.Duration;

/**
 * Contains tests for the methods of the Recipe entity, which can be found in the entities subfolder of the main
 * folder
 */
public class RecipeTest extends TestCase {

    /**
     * Setup phase which runs before every test method
     */
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Teardown phase which runs after every test method
     */
    public void tearDown()throws Exception {
    }

    /**
     * Tests whether the getName() method works as desired when all possible parameters are provided to the Recipe
     * entity
     */
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

    /**
     * Tests whether the getDescription() method works as desired when all possible parameters are provided to the
     * Recipe entity
     */
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

    /**
     * Tests whether the getIngredients() method works as desired when all possible parameters are provided to the
     * Recipe entity
     */
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

    /**
     * Tests whether the getInstructions() method works as desired when all possible parameters are provided to the
     * Recipe entity
     */
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

    /**
     * Tests whether the getCookTime() method works as desired when all possible parameters are provided to the
     * Recipe entity
     */
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

    /**
     * Tests whether the getYield() method works as desired when all possible parameters are provided to the
     * Recipe entity
     */
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

    /**
     * Tests whether the getCollection() method of the Recipe entity works as desired
     */
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
