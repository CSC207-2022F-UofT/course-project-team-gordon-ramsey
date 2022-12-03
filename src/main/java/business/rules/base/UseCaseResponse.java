package business.rules.base;

public class UseCaseResponse{
    public static enum RETURN_CODE{
        SUCCESS,
        FAILURE
    }

    public static enum ACTION_CODE{
        SHOW_DATA_STRING,
        SHOW_DATA_RECIPE,
        DO_NOTHING
    }
    
    public RETURN_CODE rCode;
    public ACTION_CODE aCode;

    public UseCaseResponse(RETURN_CODE rCode, ACTION_CODE aCode){
        this.rCode = rCode;
        this.aCode = aCode;
    }
}