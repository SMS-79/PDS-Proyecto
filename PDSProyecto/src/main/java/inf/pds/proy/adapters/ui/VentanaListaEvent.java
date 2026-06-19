package inf.pds.proy.adapters.ui;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class VentanaListaEvent extends ApplicationEvent{
	
    public VentanaListaEvent(Stage stage) {
        super(stage);
    }
    public Stage getStage() {
        return (Stage) getSource();
    }
}
