package business.rules.base.request;

import business.rules.dbs.UserDB;

/**
 * Manages request to login user
 */
public class UseCaseLoginRequest extends UseCaseRequest{
    public String username;
    public String password;
    public UserDB userDB;

    /**
     * @param stage usecase request stage
     * @param username user inputted username
     * @param password user inputted password
     * @param userDB the user database
     */
    public UseCaseLoginRequest(String username, String password, UserDB userDB, int stage) {
        super(stage);
        this.username = username;
        this.password = password;
        this.userDB = userDB;
    }
}