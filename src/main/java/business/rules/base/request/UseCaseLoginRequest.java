package business.rules.base.request;

import business.rules.dbs.UserDB;

public class UseCaseLoginRequest extends UseCaseRequest{
    public String username;
    public String password;
    public UserDB userDB;

    public UseCaseLoginRequest(String username, String password, UserDB userDB, int stage) {
        super(stage);
        this.username = username;
        this.password = password;
        this.userDB = userDB;
    }
}