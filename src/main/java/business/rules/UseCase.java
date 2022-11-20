package business.rules;

public interface UseCase{
    public UseCaseResponse process(UseCaseRequest ucr);
    public int getEndStage();
}
