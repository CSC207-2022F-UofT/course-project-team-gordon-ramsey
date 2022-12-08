package business.rules.ui;

import business.rules.UseCaseHandler;
/**
 * A Class that changes the state of the presenter when the user logs into their account.
 */
public class LoginUserChangeEvent extends ChangeEvent{
    /**
     * String representing the credentials of the user logging in
     */
    public String username, password;

    /**
     *
     * @param name the name entered by the user to login
     * @param password the password entered by the user to login
     */
    public LoginUserChangeEvent(String name, String password){
        super(UseCaseHandler.USE_CASE.USER_LOGIN_USECASE);
        this.username = name;
        this.password = password;
    }
}