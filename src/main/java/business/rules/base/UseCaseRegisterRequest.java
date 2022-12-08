package business.rules.base;

import business.rules.dbs.UserDB;

/**
 * Manages request register user
 */
public class UseCaseRegisterRequest extends UseCaseRequest{

    private String username;
    private String password;
    private String fullname;

    private UserDB udb;

    /**
     * @param stage
     * @param username
     * @param password
     * @param fullname
     * @param udb
     */
    public UseCaseRegisterRequest(int stage, String username, String password, String fullname, UserDB udb) {
        super(stage);
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.udb = udb;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public UserDB getUDB(){
        return udb;
    }
}
