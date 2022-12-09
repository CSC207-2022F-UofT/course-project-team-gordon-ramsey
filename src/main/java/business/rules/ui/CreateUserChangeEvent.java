package business.rules.ui;

import business.rules.UseCaseHandler;

public class CreateUserChangeEvent extends ChangeEvent{
    public String fullname, username, password;

    public CreateUserChangeEvent(String fullname, String username, String password){
        super(UseCaseHandler.USE_CASE.CREATE_USER_USECASE);
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }
}