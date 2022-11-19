package business.rules;

public class ChangeEvent {
    public UseCaseHandler.USE_CASES use_case_id;

    public ChangeEvent(UseCaseHandler.USE_CASES use_case){
        this.use_case_id = use_case;
    }
}
