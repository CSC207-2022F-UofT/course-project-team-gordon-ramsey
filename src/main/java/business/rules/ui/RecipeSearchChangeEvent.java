package business.rules.ui;

import business.rules.UseCaseHandler;

public class RecipeSearchChangeEvent extends ChangeEvent{
    public String keyword;
    public boolean verbose;

    public RecipeSearchChangeEvent(String keyword, boolean verbose){
        super(UseCaseHandler.USE_CASE.ADD_RECIPE_USECASE);
        this.keyword = keyword;
        this.verbose = verbose;
    }
}