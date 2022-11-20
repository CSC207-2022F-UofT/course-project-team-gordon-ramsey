package business.rules;

public class Presenter {

    private UseCaseHandler uch;
    private UI ui;

    public Presenter(UI ui){
        this.uch = new UseCaseHandler(this);
        this.ui = ui;
        if(ui == null) throw new IllegalArgumentException("Presenter initialized with a null parameter.");
    }

    public void fireEvent(ChangeEvent e){
        uch.handle(e.use_case_id, e.data);
    }

}