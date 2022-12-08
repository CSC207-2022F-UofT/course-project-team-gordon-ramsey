package business.rules.ui;

import business.rules.UseCaseHandler;

/**
 * A Class which changes the state of the presenter depending on the users action.
 *
 * Also notifies UseCaseHandler when a user action has been taken.
 */
public class ChangeEvent {
    public UseCaseHandler.USE_CASE use_case_id;

    /**
     *
     * @param use_case represents the specific use case for the UseCaseHandler to trigger
     */
    public ChangeEvent(UseCaseHandler.USE_CASE use_case){
        this.use_case_id = use_case;
    }
}
