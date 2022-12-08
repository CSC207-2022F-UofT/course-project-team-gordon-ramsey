package business.rules.base;

/**
 * Class representing the string response from the UseCases
 */
public class UseCaseStringResponse extends UseCaseResponse{
    public String str;

    /**
     *
     * @param rCode return code of success/failure
     * @param aCode action code of show data recipe/string or do nothing
     * @param str the response string
     */
    public UseCaseStringResponse(RETURN_CODE rCode, ACTION_CODE aCode, String str){
        super(rCode, aCode);
        this.str = str;
    }

    public String getStr() {
        return this.str;
    }
}
