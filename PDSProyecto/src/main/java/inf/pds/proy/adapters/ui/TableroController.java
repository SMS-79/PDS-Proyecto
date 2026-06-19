package inf.pds.proy.adapters.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import inf.pds.proy.application.usecases.TableroServiceImpl;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Tarjeta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

@Component("tableroUIController")
public class TableroController {

    private final ApplicationContext applicationContext;
    private final TableroServiceImpl tableroService;

    @Value("classpath:/views/LoginView.fxml")
    private Resource loginView;

    @FXML private Label usuarioLabel;
    @FXML private HBox contenedorListas;

    // Inyectamos el contexto de Spring y el servicio para leer de la BD
    public TableroController(ApplicationContext applicationContext, TableroServiceImpl tableroService) {
        this.applicationContext = applicationContext;
        this.tableroService = tableroService;
    }

    // Se llama desde el login para preparar la vista con el usuario que acaba de entrar
    public void inicializarTablero(String email) {
        usuarioLabel.setText("Usuario: " + email);
        cargarDatosReales();
    }

    private void cargarDatosReales() {
        // Limpiamos el contenedor por si había componentes de prueba en el FXML
        if(contenedorListas != null) {
            contenedorListas.getChildren().clear();
        }

        // Sacamos el tablero 1 (de momento lo dejamos fijo para hacer pruebas)
        Tablero tablero = tableroService.filtrarTableroByIdOrUrl(1L); 
        
        if(tablero == null) return;

        // Vamos creando una columna visual por cada lista del tablero
        for (ListaTareas lista : tablero.getListas()) {
            
            VBox columnaLista = new VBox(10); 
            columnaLista.setPrefWidth(270);
            columnaLista.setStyle("-fx-background-color: #ebecf0; -fx-background-radius: 5; -fx-padding: 10;");

            Label tituloLista = new Label(lista.getTipo());
            tituloLista.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            columnaLista.getChildren().add(tituloLista);

            // Metemos las tarjetas reales dentro de su columna correspondiente
            for (Tarjeta tarjeta : lista.getTarjetas()) {
                Button botonTarjeta = new Button(tarjeta.getNombre());
                botonTarjeta.setPrefWidth(250);
                botonTarjeta.setStyle("-fx-alignment: center-left; -fx-background-color: white; -fx-background-radius: 3;");
                columnaLista.getChildren().add(botonTarjeta);
            }

            Button btnAñadir = new Button("+ Añadir tarjeta");
            btnAñadir.setStyle("-fx-background-color: transparent; -fx-text-fill: #5e6c84;");
            columnaLista.getChildren().add(btnAñadir);

            contenedorListas.getChildren().add(columnaLista);
        }
    }

    @FXML
    void handleCerrarSesion(ActionEvent event) {
        // Cargamos la vista del login y cambiamos la escena de la ventana
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginView.getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}