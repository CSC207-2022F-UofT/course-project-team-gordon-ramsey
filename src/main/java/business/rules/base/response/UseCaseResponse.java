package business.rules.base.response;

/**
 * Class representing the response from the UseCases
 */
public class UseCaseResponse{

    public static enum RETURN_CODE{
        SUCCESS,
        FAILURE
    }

    public static enum ACTION_CODE{
        SHOW_DATA_STRING,
        SHOW_DATA_RECIPE,
        LOGIN_USER,
        ASK_USER_FIELD,
        DO_NOTHING
    }
    
    public RETURN_CODE rCode;
    public ACTION_CODE aCode;

    /**
     * @param rCode return code of success/failure
     * @param aCode action code
     */
    public UseCaseResponse(RETURN_CODE rCode, ACTION_CODE aCode){
        this.rCode = rCode;
        this.aCode = aCode;
    }
}