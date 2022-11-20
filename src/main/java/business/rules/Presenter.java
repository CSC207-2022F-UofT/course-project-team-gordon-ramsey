package business.rules;

public class Presenter {

    private UseCaseHandler uch;

    public Presenter(UseCaseHandler uch){
        this.uch = uch;
    };

    public void stateChanged(ChangeEvent e){
    }

}