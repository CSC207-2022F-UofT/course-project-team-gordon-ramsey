package business.rules.base;

import entities.Recipe;
import entities.User;

/**
 * Manages request to select recipe
 */
public class UseCaseSelectRequest extends UseCaseRequest {

    private User user;
    private Recipe recipe;

    private boolean favourite;

    /**
     *
     * @param stage
     * @param user
     * @param recipe recipe
     * @param fav boolean representing whether selected recipe is in favourites or not
     */
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
