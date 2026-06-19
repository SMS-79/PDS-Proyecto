package inf.pds.proy.adapters.ui;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import inf.pds.proy.PdsProyectoApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class AplicacionJavaFx extends Application{
	
	private ConfigurableApplicationContext context;

    @Override
    public void init() {
        // Esto arranca Spring Boot por debajo
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder()
                .sources(PdsProyectoApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Le pasamos la ventana a Spring Boot
        context.publishEvent(new VentanaListaEvent(primaryStage));
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}

