package business.rules.ui;

import business.rules.UseCaseHandler;

public class CreateUserChangeEvent extends ChangeEvent{
    public String name, username, password;

    public CreateUserChangeEvent(String name, String username, String password){
        super(UseCaseHandler.USE_CASE.CREATE_USER_USECASE);
        this.name = name;
        this.username = username;
        this.password = password;
    }
}