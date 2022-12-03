package business.rules;

public class UseCaseResponse{
    public static enum RETURN_CODE{
        SUCCESS,
        FAILURE
    }

    public static enum ACTION_CODE{
        SHOW_DATA_STRING,
        SHOW_DATA_INT,
        SHOW_DATA_FLOAT,
        DO_NOTHING
    }
    
    public RETURN_CODE rCode;
    public ACTION_CODE aCode;
    public Object[] data;

    public UseCaseResponse(RETURN_CODE rCode, ACTION_CODE aCode, Object[] data){
        this.rCode = rCode;
        this.aCode = aCode;
        this.data = data;
    }
}