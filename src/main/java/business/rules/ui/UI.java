package business.rules.ui;

import business.rules.PresenterInteractor;

public interface UI extends PresenterInteractor {
    public void showMessage(String msg);
    public void showCollection(String[][] collec);
    public void showCollection(String[][][] collec);
    public void run();
}
