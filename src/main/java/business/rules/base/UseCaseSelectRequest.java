package business.rules.base;

import entities.Recipe;
import entities.User;

public class UseCaseSelectRequest extends UseCaseRequest {

    private User user;
    private Recipe recipe;

    public UseCaseSelectRequest(int stage, User user, Recipe recipe) {
        super(stage);
        this.user = user;
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
