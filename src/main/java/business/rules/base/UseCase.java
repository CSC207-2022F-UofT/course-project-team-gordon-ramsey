package business.rules.base;

import business.rules.base.request.UseCaseRequest;
import business.rules.base.response.UseCaseResponse;

public interface UseCase{
    public UseCaseResponse process(UseCaseRequest ucr);
    public int getEndStage();
    public String getJob();
}
