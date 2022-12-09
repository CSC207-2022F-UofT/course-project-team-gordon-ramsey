package business.rules.ui;

import business.rules.UseCaseHandler;
import entities.Recipe;
import entities.User;

public class AddToFavoritesChangeEvent extends ChangeEvent{
    public Recipe recipe;
    public User user;

    public AddToFavoritesChangeEvent() {
        super(UseCaseHandler.USE_CASE.ADD_TO_FAVORITES_USECASE);
        this.recipe = null;
        this.user = null;
    }

    public AddToFavoritesChangeEvent(Recipe recipe, User user) {
        super(UseCaseHandler.USE_CASE.ADD_TO_FAVORITES_USECASE);
        this.recipe = recipe;
        this.user = user;
    }
}