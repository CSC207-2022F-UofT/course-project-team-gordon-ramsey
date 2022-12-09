package business.rules.base;

import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;

/**
 * Interface implemented by useCases
 */
public interface UseCase{

    /**
     * Process the UseCase requests and triggers the response
     * @param ucr UseCase request
     * @return response to the request
     */
    public UseCaseResponse process(UseCaseRequest ucr);
    public int getEndStage();
    public String getJob();
}
