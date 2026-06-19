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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Component("tableroUIController")
public class TableroController {

    private final ApplicationContext applicationContext;
    private final TableroServiceImpl tableroService;

    @Value("classpath:/views/LoginView.fxml")
    private Resource loginView;

    @FXML private Label usuarioLabel;
    @FXML private HBox contenedorListas;

    // Inyectamos el contexto de Spring y el servicio para leer/escribir de la BD
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
        
        //Optional<Tablero> tableroOpt = tableroService.filtrarTableroByIdOrUrl("1"); 
        
        //if(tableroOpt.isEmpty()) return;
        
        Tablero tablero = tableroService.obtenerTableros().getFirst();

        // Vamos creando una columna visual por cada lista del tablero
        for (ListaTareas lista : tablero.getListas()) {
            
            VBox columnaLista = new VBox(10); 
            columnaLista.setPrefWidth(270);
            columnaLista.setStyle("-fx-background-color: #ebecf0; -fx-background-radius: 5; -fx-padding: 10;");

            Label tituloLista = new Label(lista.getTipo());
            tituloLista.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            columnaLista.getChildren().add(tituloLista);

            // Metemos las tarjetas reales dentro de su columna correspondiente
            if (lista.getTarjetas() != null) {
                for (Tarjeta tarjeta : lista.getTarjetas()) {
                    Button botonTarjeta = new Button(tarjeta.getNombre());
                    botonTarjeta.setPrefWidth(250);
                    botonTarjeta.setStyle("-fx-alignment: center-left; -fx-background-color: white; -fx-background-radius: 3;");
                    columnaLista.getChildren().add(botonTarjeta);
                }
            }

            // Creamos el botón de añadir tarjeta
            Button btnAñadir = new Button("+ Añadir tarjeta");
            btnAñadir.setStyle("-fx-background-color: transparent; -fx-text-fill: #5e6c84;");
            
            // Le damos acción al botón para que pregunte el nombre de la nueva tarea
            btnAñadir.setOnAction(e -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Nueva Tarjeta");
                dialog.setHeaderText("Añadir tarjeta a la lista: " + lista.getTipo());
                dialog.setContentText("Nombre de la tarea:");

                // Si el usuario escribe algo y le da a Aceptar...
                Optional<String> resultado = dialog.showAndWait();
                resultado.ifPresent(nombreTarea -> {
                    try {
                        // Llamamos al servicio real para crear la tarea en la BD
                        // Pasamos null a lo que no tenemos aún (etiqueta, fecha, responsable)
                        tableroService.crearTarjetaTarea(
                            tablero.getId(), 
                            lista.getId(), 
                            nombreTarea, 
                            null, 
                            LocalDate.now(), 
                            tablero.getPropietario(), 
                            ""
                        );
                        
                        System.out.println("Tarea '" + nombreTarea + "' creada en la BD!");
                        
                        // Recargamos el tablero para que la nueva tarjeta aparezca al instante
                        cargarDatosReales();
                    } catch (Exception ex) {
                        System.out.println("Error al crear la tarea: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                });
            });

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