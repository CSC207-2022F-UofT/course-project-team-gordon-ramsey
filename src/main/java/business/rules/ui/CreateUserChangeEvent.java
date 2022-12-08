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
    public String name, username, password;

    /**
     *
     * @param name name of the user in the application associated with their profile
     * @param username the username for the user
     * @param password the password of the user
     */
    public CreateUserChangeEvent(String name, String username, String password){
        super(UseCaseHandler.USE_CASE.CREATE_USER_USECASE);
        this.name = name;
        this.username = username;
        this.password = password;
    }
}