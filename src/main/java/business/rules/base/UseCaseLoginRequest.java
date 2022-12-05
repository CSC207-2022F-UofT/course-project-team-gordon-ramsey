package business.rules.base;

import business.rules.dbs.UserDB;

public class UseCaseLoginRequest extends UseCaseRequest{

    private String username;
    private String password;
    private UserDB userDB;

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
