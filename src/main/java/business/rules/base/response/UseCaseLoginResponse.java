package business.rules.base.response;

import entities.User;

/**
 * Class representing the response to successfull user login.
 */
public class UseCaseLoginResponse extends UseCaseResponse {
    public User user;

    /**
     * @param rCode return code of success / failure.
     * @param aCode action code.
     * @param user user to log in.
     */
    public UseCaseLoginResponse(RETURN_CODE rCode, ACTION_CODE aCode, User user){
        super(rCode, aCode);
        this.user = user;
    }
}