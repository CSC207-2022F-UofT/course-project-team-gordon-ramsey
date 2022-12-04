package business.rules.ui;

import business.rules.UseCaseHandler;

public class LoginUserChangeEvent extends ChangeEvent{
    public String name, password;

    public LoginUserChangeEvent(String name, String password){
        super(UseCaseHandler.USE_CASE.USER_LOGIN_USECASE);
        this.name = name;
        this.password = password;
    }
}