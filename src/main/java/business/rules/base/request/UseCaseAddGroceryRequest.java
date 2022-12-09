package business.rules.base.request;

import entities.Recipe;
import entities.User;

/**
 * Manages request to add ingredients from specific recipe to grocery list
 */
public class UseCaseAddGroceryRequest extends UseCaseRequest {
    public Recipe recipe;
    public User user;

    /**
     * @param stage usecase request stage
     * @param recipe user selected recipe
     * @param user the user
     */
    public UseCaseAddGroceryRequest(Recipe recipe, User user, int stage) {
        super(stage);
        this.user = user;
        this.recipe = recipe;
    }
}