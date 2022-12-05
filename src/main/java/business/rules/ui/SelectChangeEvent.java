package business.rules.ui;

import business.rules.UseCaseHandler;

public class SelectChangeEvent extends ChangeEvent{

    public String recipe;
    public boolean favourite;

    public SelectChangeEvent(String recipe,
                             boolean favourite) {
        super(UseCaseHandler.USE_CASE.SELECT_RECIPE_USECASE);
        this.recipe = recipe;
        this.favourite = favourite;
    }
}
