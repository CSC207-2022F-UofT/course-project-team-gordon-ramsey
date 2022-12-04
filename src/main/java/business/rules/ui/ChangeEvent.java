package business.rules.ui;

import business.rules.UseCaseHandler;

public class ChangeEvent {
    public UseCaseHandler.USE_CASE use_case_id;

    public ChangeEvent(UseCaseHandler.USE_CASE use_case){
        this.use_case_id = use_case;
    }
}
