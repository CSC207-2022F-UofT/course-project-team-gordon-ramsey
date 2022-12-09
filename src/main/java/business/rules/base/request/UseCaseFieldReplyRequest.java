package business.rules.base.request;

/*
 * Manages request of user having replies to a field modification request.
 */
public class UseCaseFieldReplyRequest extends UseCaseRequest{
    public String[] field_response;

    /**
     * @param field_response new field after modification by user.
     * @param stage usecase stage.
     */
    public UseCaseFieldReplyRequest(String[] field_response, int stage){
        super(stage);
        this.field_response = field_response;
    }
}