package business.rules;

public interface UI {
    public void showMessage(String msg);
    public void showCollection(String[][] collec);
    public void setPresenter(Presenter presenter);
}
