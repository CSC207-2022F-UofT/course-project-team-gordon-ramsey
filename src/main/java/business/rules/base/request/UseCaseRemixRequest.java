package business.rules.base.request;

import entities.Recipe;
import entities.User;

public class UseCaseRemixRequest extends UseCaseRequest{
    public Recipe original_recipe;
    public User user;

    public UseCaseRemixRequest(Recipe original_recipe, User user, int stage) {
        super(stage);
        this.user = user;
        this.original_recipe = original_recipe;
    }
}
