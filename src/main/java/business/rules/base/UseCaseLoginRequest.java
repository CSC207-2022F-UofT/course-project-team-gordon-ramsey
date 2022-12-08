package business.rules.base;

import business.rules.dbs.UserDB;

/**
 * Manages request to login user
 */
public class UseCaseLoginRequest extends UseCaseRequest{

    private String username;
    private String password;
    private UserDB userDB;

    /**
     * Constructor with stage, username, password, userDB
     * @param stage useCase request stage
     * @param username user's username
     * @param password user's password
     * @param userDB the user database
     */
    public UseCaseLoginRequest(int stage, String username, String password, UserDB userDB) {
        super(stage);
        this.username = username;
        this.password = password;
        this.userDB = userDB;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserDB getUserDB() {
        return userDB;
    }
}
