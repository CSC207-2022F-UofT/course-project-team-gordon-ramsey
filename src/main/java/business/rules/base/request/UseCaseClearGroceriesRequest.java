package business.rules.base.request;

import entities.User;

/*
 * Manages request to clear user's grocery list.
 */
public class UseCaseClearGroceriesRequest extends UseCaseRequest{
    public User user;

    /**
     * @param user concerned user.
     * @param stage usecase stage.
     */
    public UseCaseClearGroceriesRequest(User user, int stage) {
        super(stage);
        this.user = user;
    }
}