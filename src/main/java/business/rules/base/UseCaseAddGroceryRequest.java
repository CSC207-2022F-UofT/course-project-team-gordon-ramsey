package business.rules.base;

import entities.Recipe;
import entities.User;

public class UseCaseAddGroceryRequest extends UseCaseRequest {

    public Recipe[] recipes;
    public User user;

    public UseCaseAddGroceryRequest(Recipe[] recipes, User user, int stage) {
        super(stage);
        this.user = user;
        this.recipes = recipes;
    }
}
