package inf.pds.proy.adapters.rest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioApiClient {

    private static final String USUARIOS_URL = "http://localhost:8080/api/usuarios";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public UsuarioApiClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public void crearUsuario(String nombre, String email, String pswd) throws IOException, InterruptedException{
        String json = objectMapper.writeValueAsString(
            Map.of(
                "nombre", nombre,
                "email", email,
                "pswd", pswd
            )
        );

        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(USUARIOS_URL))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(json))
                            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 409){
            throw new IllegalArgumentException(
                "Ya existe un usuario con ese correo electronico"
            );
        }
        if (response.statusCode() != 200){
            throw new IllegalStateException(
                "No se pudo crear el usuario." + response.statusCode()
            );
        }
    }

    public void iniciarSesion(String email, String pswd) throws IOException, InterruptedException{

        String json = objectMapper.writeValueAsString(Map.of("email", email, "pswd", pswd));

        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(USUARIOS_URL + "/login"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(json))
                            .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 401){
            throw new IllegalArgumentException(
                "Correo o contraseña incorrectos"
            );
        }
        if(response.statusCode() == 400){
            throw new IllegalArgumentException(
                "Rellena el correo y la contraseña"
            );
        }
        if (response.statusCode() != 200){
            throw new IllegalStateException(
                "No se pudo iniciar sesión." + response.statusCode()
            );
        }

    }
}
