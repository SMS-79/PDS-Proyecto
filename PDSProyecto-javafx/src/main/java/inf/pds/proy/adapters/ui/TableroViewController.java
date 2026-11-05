package inf.pds.proy.adapters.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import inf.pds.proy.adapters.rest.TableroApiClient;
import inf.pds.proy.model.ListaTareaModel;
import inf.pds.proy.model.TableroModel;
import inf.pds.proy.model.TarjetaModel;

public class TableroViewController {

    @FXML
    private Label usuarioLabel;
    @FXML
    private HBox contenedorListas;
    @FXML 
    private Label tituloTableroLabel;
    @FXML 
    private Menu misTablerosMenu;

    private String emailUsuarioActual;

    private TableroModel tableroActual;

    private final TableroApiClient tableroApiClient = new TableroApiClient();

    // Se llama desde el login para preparar la vista con el usuario que acaba de
    // entrar
    public void inicializarTablero(String email) {
        this.emailUsuarioActual = email.strip();
        usuarioLabel.setText("Usuario: " + emailUsuarioActual);
        tituloTableroLabel.setText("Sin tablero seleccionado");
        contenedorListas.getChildren().clear();
        try{
            cargarDatosReales();
        } catch(Exception e){
            System.out.println("Error al cargar los tableros: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

    private void cargarDatosReales() throws IOException, InterruptedException{
        List<TableroModel> tableros = tableroApiClient.obtenerTablerosByEmail(emailUsuarioActual);
        misTablerosMenu.getItems().clear();
        for(TableroModel tablero : tableros){
            agregarTableroAlMenu(tablero);
        }

        if(tableros.isEmpty()){
            tableroActual = null;
            tituloTableroLabel.setText("Sin tableros");
            contenedorListas.getChildren().clear();
            contenedorListas.getChildren().add(new Label("Todavia no tienes tableros. Usa Tableros >  Nuevo tablero..."));
            return;
        }

        if(tableroActual != null){
            for(TableroModel tablero : tableros){
                if(tablero.getTableroId().getId().equals(tableroActual.getTableroId().getId())){
                    mostrarTablero(tablero);
                    return;
                }
            }
        }
        mostrarTablero(tableros.getFirst());
    }

    private void agregarTableroAlMenu(TableroModel tablero){
        MenuItem opcion = new MenuItem(tablero.getNombre());
        misTablerosMenu.getItems().add(opcion);
        opcion.setOnAction(e -> mostrarTablero(tablero));
    }
    
    private void mostrarTablero(TableroModel tablero) { 
        this.tableroActual = tablero;
        tituloTableroLabel.setText(tablero.getNombre());
        contenedorListas.getChildren().clear();
        if(tablero.getListas().isEmpty()){
            contenedorListas.getChildren().add(new Label("Este tablero todavía no tiene listas."));
            return;
        }
        for (ListaTareaModel lista : tablero.getListas()) {

            VBox columnaLista = new VBox(10);
            columnaLista.setPrefWidth(270);
            columnaLista.setStyle("-fx-background-color: #ebecf0; -fx-background-radius: 5; -fx-padding: 10;");

            Label tituloLista = new Label(lista.getTipo());
            tituloLista.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            columnaLista.getChildren().add(tituloLista);

            // Metemos las tarjetas reales dentro de su columna correspondiente
            if (lista.getTarjetas() != null) {
                for (TarjetaModel tarjeta : lista.getTarjetas()) {
                    Button botonTarjeta = new Button(tarjeta.getNombre());
                    botonTarjeta.setPrefWidth(250);
                    botonTarjeta.setStyle(
                            "-fx-alignment: center-left; -fx-background-color: white; -fx-background-radius: 3;");
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
                        // Llamamos al puerto de entrada, la UI usa el servicio
                        // Pasamos null a lo que no tenemos aún (etiqueta, fecha, responsable)
                        tableroApiClient.crearTarjetaTarea(
                            tablero.getTableroId().getId(), 
                            lista.getListaId().getId(), 
                            nombreTarea, 
                            null, 
                            null, 
                            LocalDate.now(), 
                            tablero.getPropietario().getUsuarioId().getId(), 
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
    void handleNuevoTablero() {
        TextInputDialog text = new TextInputDialog();
        text.setTitle("Nuevo Tablero");
        text.setHeaderText("Crear un tablero");
        text.setContentText("Nombre del tablero:");

        Optional<String> resultado = text.showAndWait();

        resultado.ifPresent(nombreTablero -> {
            if (nombreTablero.isBlank()) {
                System.out.println("Escribe un nombre para el tablero");
                return;
            }   
            try{
                TableroModel nuevoTablero = tableroApiClient.crearTablero(nombreTablero.strip(), emailUsuarioActual.strip());
                agregarTableroAlMenu(nuevoTablero);
                mostrarTablero(nuevoTablero);
                System.out.println("Tablero " + nuevoTablero.getNombre() + " creado en la base de datos");
            } catch(Exception e){ 
                System.out.println("Error al crear el tablero: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    @FXML 
    void handleAbrirTableroCompartido () {
        TextInputDialog text = new TextInputDialog();
        text.setTitle("Abrir tablero compartido");
        text.setHeaderText("Acceder mediante la URL a un tablero:");
        text.setContentText("URl del tablero:");

        Optional<String> resultado = text.showAndWait();

        resultado.ifPresent(url -> {
            if(url.isBlank()){
                System.out.println("Escribe la URL del tablero");
                return;
            }

            System.out.println("URL introducida: " + url.strip());
        });
    }

    @FXML
    void handleCerrarSesion(ActionEvent event) {
        // Cargamos la vista del login y cambiamos la escena de la ventana
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/views/LoginView.fxml"));
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