package business.rules.base.request;

import entities.Recipe;
import entities.User;

public class UseCaseSelectRequest extends UseCaseRequest {

    private User user;
    private Recipe recipe;

    private boolean favourite;

    public UseCaseSelectRequest(int stage, User user, Recipe recipe, boolean fav) {
        super(stage);
        this.user = user;
        this.recipe = recipe;
        this.favourite = fav;
    }

    public User getUser() {
        return user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean getFavourite(){
        return favourite;
    }
}
