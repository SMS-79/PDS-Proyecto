package inf.pds.proy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PdsProyectoApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(PdsProyectoApplication.class);
    }

    @Override
    public void start(Stage primaryStage) {
        Label titulo = new Label("Bienvenido mamawebo");
        StackPane root = new StackPane(titulo);
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("Aplicación de trollero");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.close();
        Platform.exit();
    }

    public static void main(String[] args) {
        Application.launch(PdsProyectoApplication.class, args);
    }
}
