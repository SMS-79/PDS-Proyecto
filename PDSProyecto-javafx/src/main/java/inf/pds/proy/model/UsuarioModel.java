package inf.pds.proy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioModel {

    private IdentificadorModel id;
    private String nombre;
    private String email;

    public UsuarioModel() {
    }

    public IdentificadorModel getId() {
        return id;
    }

    public void setId(IdentificadorModel id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
