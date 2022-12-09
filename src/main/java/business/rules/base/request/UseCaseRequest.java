package business.rules.base.request;

/**
 * Manages use case request; stage of request
 */
public class UseCaseRequest{
    public int stage;

    /**
     * @param stage stage of useCase request
     */
    public UseCaseRequest(int stage){
        this.stage = stage;
    }
}
