package business.rules.base.request;

import business.rules.dbs.UserDB;

/**
 * Manages request register user
 */
public class UseCaseRegisterRequest extends UseCaseRequest{
    public String username;
    public String password;
    public String fullname;
    public UserDB udb;

    /**
     * @param stage usecase request stage
     * @param username user inputted username
     * @param password user inputted password
     * @param fullname user's fullname
     * @param udb user database
     */
    public UseCaseRegisterRequest(String username, String password, String fullname, UserDB udb, int stage) {
        super(stage);
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.udb = udb;
    }
}