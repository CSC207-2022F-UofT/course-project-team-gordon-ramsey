package business.rules.ui;

import business.rules.PresenterInteractor;

/**
 * Interface which is implemented by the external user interfaces (UI)
 */
public interface UI extends PresenterInteractor{
    public static enum MODIFICATION_TYPE{
        EDIT_VALUES,
        ADD_REMOVE_VALUES,
        EDIT_AND_ADD_REMOVE_VALUES
    }

    public static enum FIELD_TYPE{
        INTEGER,
        FLOAT,
        STRING
    }

    /**
     * @param msg message to be shows to user
     */
    public void showMessage(String msg);

    /**
     * @param collec represents a collection object (2D string array)
     */
    public void showCollection(String[][] collec);

    /**
     * @param collec represents a collection object (3D string array)
     */
    public void showCollection(String[][][] collec);
    public void showCollectionDivider(String desc);
    public String[] requestCollectionFieldModification(String[] field, MODIFICATION_TYPE mtype, FIELD_TYPE ftype);
    public void run();
}