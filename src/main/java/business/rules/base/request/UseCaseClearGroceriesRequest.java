package business.rules.base.request;

import entities.User;

public class UseCaseClearGroceriesRequest extends UseCaseRequest{
    public User user;

    public UseCaseClearGroceriesRequest(User user, int stage) {
        super(stage);
        this.user = user;
    }
}