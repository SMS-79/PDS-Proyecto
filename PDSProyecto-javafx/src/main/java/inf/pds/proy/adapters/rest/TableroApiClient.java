package inf.pds.proy.adapters.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import inf.pds.proy.model.TableroModel;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TableroApiClient {

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

        // GET http://localhost:8080/api/tableros
        // JSON → List<TableroModel>
    }

    public void crearTarjetaTarea(Long tableroId, Long listaId, String nombre) {
        // POST /api/tableros/{tableroId}/listas/{listaId}/tarjeta
    }
}
