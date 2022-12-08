package business.rules.ui;

import business.rules.UseCaseHandler;
/**
 * A Class that changes the state of the presenter when the user searches for a recipe.
 *
 */
public class RecipeSearchChangeEvent extends ChangeEvent{
    /**
     * String representing keyword searched by user
     */
    public String keyword;
    /**
     * boolean representing verbosity of user input
     */
    public boolean verbose;

    /**
     *
     * @param keyword the word inputted by the user into the program
     * @param verbose represents if the keyword used the appropriate number of words
     */
    public RecipeSearchChangeEvent(String keyword, boolean verbose){
        super(UseCaseHandler.USE_CASE.ADD_RECIPE_USECASE);
        this.keyword = keyword;
        this.verbose = verbose;
    }
}