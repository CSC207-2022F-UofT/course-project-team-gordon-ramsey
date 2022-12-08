package business.rules.base;

/**
 * Manages use case request; stage of request
 */
public class UseCaseRequest{
    public int stage;

    /**
     *
     * @param stage
     */
    public UseCaseRequest(int stage){
        this.stage = stage;
    }
}
