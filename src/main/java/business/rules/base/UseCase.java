package business.rules.base;

public interface UseCase{
    public UseCaseResponse process(UseCaseRequest ucr);
    public int getEndStage();
    public String getJob();
}
