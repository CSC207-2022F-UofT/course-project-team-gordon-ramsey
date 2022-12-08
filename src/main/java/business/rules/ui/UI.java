package business.rules.ui;

import business.rules.PresenterInteractor;

public interface UI extends PresenterInteractor{
    public static enum MODIFICATION_TYPE{
        EDIT_VALUES,
        ADD_REMOVE_VALUES,
        EDIT_AND_ADD_REMOVE_VALUES
    }
    public static enum FIELD_TYPE{
        INTEGER,
        FLOAT,
        STRING,
        ORDERED_INT_WORD_STRING
    }
    public void showMessage(String msg);
    public void showCollection(String[][] collec);
    public void showCollection(String[][][] collec);
    public void showCollectionDivider(String desc);
    public String[] requestCollectionFieldModification(String[] field, MODIFICATION_TYPE mtype, FIELD_TYPE ftype);
    public void run();
}