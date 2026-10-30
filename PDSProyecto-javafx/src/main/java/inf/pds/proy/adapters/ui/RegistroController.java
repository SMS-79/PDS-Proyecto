package inf.pds.proy.adapters.ui;

import java.io.IOException;

import inf.pds.proy.adapters.rest.UsuarioApiClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

    @FXML 
    private Label mensajeError;

    @FXML 
    private TextField nombreInput;

    @FXML 
    private TextField emailInput;

    @FXML 
    private TextField pswdInput;

    @FXML 
    private Button registrarButton; 

    private final UsuarioApiClient usuarioApiClient = Configuracion.getInstancia().getUsuarioApiClient();

    @FXML
    void handleRegister(ActionEvent e) {
        String nombre = nombreInput.getText().strip();
        String email = emailInput.getText().strip();
        String pswd = pswdInput.getText();

        mensajeError.setStyle("-fx-text-fill: #de350b;");

        if (nombre.isBlank() || email.isBlank() || pswd.isBlank()) {
            mensajeError.setText("Rellena todos los campos");
            mensajeError.setVisible(true);
            return;
        }

        try {
            usuarioApiClient.crearUsuario(nombre, email, pswd);

            pswdInput.clear();
            mensajeError.setStyle("-fx-text-fill: #00875a;");
            mensajeError.setText("Cuenta creada. Vuelve al inicio de sesión.");

        } catch (IllegalArgumentException error) {
            mensajeError.setText(error.getMessage());

        } catch (IOException | IllegalStateException error) {
            mensajeError.setText(
                "No se pudo completar el registro. Comprueba el servidor."
            );

        } catch (InterruptedException error) {
            mensajeError.setText("Se interrumpió el registro");
        }

        mensajeError.setVisible(true);
    }


    @FXML 
    void handleBackToLogin(ActionEvent e){
        try{ 
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/LoginView.fxml")
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) e.getSource())
                            .getScene().getWindow();

            stage.setScene(new Scene(root, 800, 600));
            stage.centerOnScreen();
        } catch (IOException exception){
            exception.printStackTrace();
            mensajeError.setText("No se pudo abrir el formulario de inicio de sesión");
            mensajeError.setVisible(true);
        }
    }

}
