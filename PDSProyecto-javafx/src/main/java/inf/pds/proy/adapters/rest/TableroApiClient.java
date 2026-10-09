package inf.pds.proy.adapters.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import inf.pds.proy.model.TableroModel;
import inf.pds.proy.model.TipoTarjeta;
import javafx.scene.AccessibleAttribute.ToggleState;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableroApiClient {

    record CrearTarjetaTareaRequest(
        String nombre, 
        String etiquetaNombre, 
        String etiquetaColor, 
        LocalDate fechaLimite, 
        Long responsable, 
        TipoTarjeta tipoTarjeta,
        String descripcion
    ) {}
    

    private static final String TABLEROS_URL = "http://localhost:8080/api/tableros";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public TableroApiClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModules(new JavaTimeModule());
    }

    public List<TableroModel> obtenerTableros() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TABLEROS_URL))
                .GET()
                .build();

        try{
            HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

            if(response.statusCode() != 200){
                throw new IllegalStateException("Error al obtener los tableros: " + response.statusCode());
            }

            return objectMapper.readValue(
                response.body(),
                new TypeReference<List<TableroModel>>() {}
            );

        }catch(IOException e){
            throw new IllegalStateException("No se pudo contactar con el backend", e);
        }catch(InterruptedException e){
            throw new IllegalStateException("La llamada al backend fue interrumpida", e);
        }

        
    }

    public void crearTarjetaTarea(Long tableroId, Long listaId, String nombre, String etiquetaNombre, String etiquetaColor, LocalDate fechaLimite, Long responsableId, String descripcion) throws IOException, InterruptedException{
        // POST /api/tableros/{tableroId}/listas/{listaId}/tarjeta

        CrearTarjetaTareaRequest datos = new CrearTarjetaTareaRequest(nombre, etiquetaNombre, etiquetaColor, fechaLimite, responsableId, TipoTarjeta.Tarea, descripcion);
        
        HttpResponse<String> response = post("/" + tableroId + "/listas/" + listaId + "/tarjeta", datos);

        if(response.statusCode() != 200){
            throw new IllegalStateException("Error al crear la tarjeta: " + response.statusCode());
        }

    }

    private HttpResponse<String> post(String ruta, Object body) throws IOException, InterruptedException{

        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(TABLEROS_URL + ruta))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
    }
}
