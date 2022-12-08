package business.rules.ui;

import business.rules.PresenterInteractor;

/**
 * Interface which is implemented by the External interfaces (UI)
 */
public interface UI extends PresenterInteractor {
    /**
     *
     * @param msg message to be shows to user
     */
    public void showMessage(String msg);

    /**
     *
     * @param collec represents a collection object (2D string array)
     */
    public void showCollection(String[][] collec);

    /**
     *
     * @param collec represents a collection object (3D string array)
     */
    public void showCollection(String[][][] collec);
    public void run();
}
