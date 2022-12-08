package business.rules.base.request;

public class UseCaseFieldReplyRequest extends UseCaseRequest{
    public String[] field_response;

    public UseCaseFieldReplyRequest(String[] field_response, int stage){
        super(stage);
        this.field_response = field_response;
    }
}