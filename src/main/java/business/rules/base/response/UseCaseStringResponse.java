package business.rules.base.response;

public class UseCaseStringResponse extends UseCaseResponse{
    public String str;

    public UseCaseStringResponse(RETURN_CODE rCode, ACTION_CODE aCode, String str){
        super(rCode, aCode);
        this.str = str;
    }
}