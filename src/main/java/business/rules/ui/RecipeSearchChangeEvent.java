package business.rules.ui;

import business.rules.UseCaseHandler;
/**
 * A Class that changes the state of the presenter when the user searches for a recipe.
 */
public class RecipeSearchChangeEvent extends ChangeEvent{
    public String keyword;
    public boolean verbose;

    /**
     * @param keyword the word inputted by the user into the program.
     * @param verbose represents if live status updates wanted.
     */
    public RecipeSearchChangeEvent(String keyword, boolean verbose){
        super(UseCaseHandler.USE_CASE.SEARCH_RECIPE_USECASE);
        this.keyword = keyword;
        this.verbose = verbose;
    }
}