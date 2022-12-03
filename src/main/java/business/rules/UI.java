package business.rules;

public interface UI extends PresenterInteractor {
    public void showMessage(String msg);
    public void showCollection(String[][] collec);
}
