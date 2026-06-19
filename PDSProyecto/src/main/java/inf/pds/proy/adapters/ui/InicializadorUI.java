package inf.pds.proy.adapters.ui;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Component
public class InicializadorUI implements ApplicationListener<VentanaListaEvent> {
	@Override
    public void onApplicationEvent(VentanaListaEvent event) {
        Stage stage = event.getStage();
        
        // Creamos un texto y un panel básico
        Label label = new Label("PRUEBA VENTANA PRINCIPAL");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 800, 600); // Ventana de 800x600 px
        
        stage.setTitle("PDS prueba interfaz");
        stage.setScene(scene);
        stage.show(); // ¡Mostrar ventana!
    }
}
	

