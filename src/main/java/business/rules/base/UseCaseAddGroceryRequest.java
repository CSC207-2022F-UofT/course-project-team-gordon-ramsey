package business.rules.base;

import entities.Recipe;
import entities.User;

public class UseCaseAddGroceryRequest extends UseCaseRequest {

    public Recipe recipe;
    public User user;

    public UseCaseAddGroceryRequest(Recipe recipe, User user, int stage) {
        super(stage);
        this.user = user;
        this.recipe = recipe;
    }
}
