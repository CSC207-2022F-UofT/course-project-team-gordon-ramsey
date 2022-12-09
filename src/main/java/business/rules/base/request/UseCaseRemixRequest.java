package business.rules.base.request;

import entities.Recipe;
import entities.User;

/**
 * Manages request to remix recipe
 */
public class UseCaseRemixRequest extends UseCaseRequest{
    public Recipe original_recipe;
    public User user;

    /**
     * @param original_recipe original starting point recipe.
     * @param user concerned user.
     * @param stage usecase stage.
     */
    public UseCaseRemixRequest(Recipe original_recipe, User user, int stage) {
        super(stage);
        this.user = user;
        this.original_recipe = original_recipe;
    }
}
