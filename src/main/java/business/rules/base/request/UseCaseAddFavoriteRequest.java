package business.rules.base.request;

import entities.Recipe;
import entities.User;

/*
 * Manages request of adding selected recipe to user's favorites in journal.
 */
public class UseCaseAddFavoriteRequest extends UseCaseRequest {
    public Recipe recipe;
    public User user;

    /**
     * @param recipe selected recipe.
     * @param user concerned user.
     * @param stage usecase stage.
     */
    public UseCaseAddFavoriteRequest(Recipe recipe, User user, int stage) {
        super(stage);
        this.user = user;
        this.recipe = recipe;
    }
}