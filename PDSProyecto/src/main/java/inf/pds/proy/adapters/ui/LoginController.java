package inf.pds.proy.adapters.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

@Component
public class LoginController {

    private final ApplicationContext applicationContext;
    
    @Value("classpath:/views/TableroView.fxml")
    private Resource tableroView;

    @FXML private TextField emailInput;
    @FXML private PasswordField passwordInput;
    @FXML private Label mensajeError;

    // Inyectamos el contexto de Spring para poder instanciar otras ventanas luego
    public LoginController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String email = emailInput.getText();
        String pwd = passwordInput.getText();

        // Comprobamos que no se hayan dejado ningún campo en blanco
        if (email.isBlank() || pwd.isBlank()) {
            mensajeError.setText("Rellena todos los campos");
            mensajeError.setStyle("-fx-text-fill: red;");
            mensajeError.setVisible(true);
        } else {
            try {
                // Cargamos el archivo visual del tablero
                FXMLLoader fxmlLoader = new FXMLLoader(tableroView.getURL());
                fxmlLoader.setControllerFactory(applicationContext::getBean);
                Parent root = fxmlLoader.load();
                
                // Le pasamos el email al controlador del tablero para que lo muestre arriba
                TableroController tableroController = fxmlLoader.getController();
                tableroController.inicializarTablero(email);

                // Sacamos la ventana actual a partir del botón y le cambiamos la escena
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1024, 768);
                stage.setScene(scene);
                stage.centerOnScreen();
                
            } catch (IOException e) {
                e.printStackTrace();
                mensajeError.setText("Error al cargar el tablero");
                mensajeError.setVisible(true);
            }
        }
    }
}