package inf.pds.proy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import inf.pds.proy.adapters.ui.AplicacionJavaFx;
import javafx.application.Application;

@SpringBootApplication
public class PdsProyectoApplication {

	public static void main(String[] args) {
		
		Application.launch(AplicacionJavaFx.class, args);
	}

}
