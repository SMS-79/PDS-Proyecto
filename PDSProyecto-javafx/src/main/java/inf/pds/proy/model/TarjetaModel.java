package inf.pds.proy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TarjetaModel {

    private IdentificadorModel id;
    private String nombre;
    private String descripcion;

    public TarjetaModel() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
