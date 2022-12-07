package business.rules.base.request;

import business.rules.dbs.UserDB;

public class UseCaseRegisterRequest extends UseCaseRequest{
    public String username;
    public String password;
    public String fullname;
    public UserDB udb;

    public UseCaseRegisterRequest(String username, String password, String fullname, UserDB udb, int stage) {
        super(stage);
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.udb = udb;
    }
}