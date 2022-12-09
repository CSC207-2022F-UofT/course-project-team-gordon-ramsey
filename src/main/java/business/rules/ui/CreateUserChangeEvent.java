package business.rules.ui;

import business.rules.UseCaseHandler;

/**
 * A Class that changes the state of the presenter when the user would like to create an account.
 *
 */
public class CreateUserChangeEvent extends ChangeEvent{
    /**
     * Strings representing the credentials of the user being created
     */
    public String fullname, username, password;

    public CreateUserChangeEvent(String fullname, String username, String password){
        super(UseCaseHandler.USE_CASE.CREATE_USER_USECASE);
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }
}