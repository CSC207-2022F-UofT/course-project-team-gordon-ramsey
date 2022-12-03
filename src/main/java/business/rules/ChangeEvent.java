package business.rules;

import business.rules.base.UseCaseRemixRequest;
import business.rules.base.UseCaseRequest;

public class ChangeEvent {
    public UseCaseHandler.USE_CASE use_case_id;
    public Object[] data;

    public ChangeEvent(UseCaseHandler.USE_CASE use_case, Object[] data){
        this.use_case_id = use_case;
        this.data = data;
    }
}
