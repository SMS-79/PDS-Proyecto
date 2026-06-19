package inf.pds.proy.adapters.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

@Component
public class InicializadorUI implements ApplicationListener<VentanaListaEvent> {

    private final ApplicationContext applicationContext;
    
    // Leemos el archivo FXML (lo vamos a crear en el siguiente paso)
    @Value("classpath:/views/LoginView.fxml")
    private Resource loginView;

    public InicializadorUI(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(VentanaListaEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginView.getURL());
            
            // Le decimos a JavaFX que use Spring para crear los controladores
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 800, 600);
            
            Stage stage = event.getStage();
            stage.setTitle("PDS Proyecto");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la interfaz FXML", e);
        }
    }
}