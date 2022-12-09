package business.rules.base.response;

import entities.User;

public class UseCaseLoginResponse extends UseCaseResponse {
    public User user;

    public UseCaseLoginResponse(RETURN_CODE rCode, ACTION_CODE aCode, User user){
        super(rCode, aCode);
        this.user = user;
    }
}