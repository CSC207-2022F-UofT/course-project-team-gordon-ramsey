package business.rules;

public class UseCaseRequest{
    public Object[] data;
    public int stage;

    public UseCaseRequest(Object[] data, int stage){
        this.data = data;
        this.stage = stage;
    }
}
