package inf.pds.proy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TarjetaModel {

    @JsonProperty ("id")
    private IdentificadorModel tarjetaId;
    private String nombre;
    private String descripcion;

    public TarjetaModel() {
    }

    public IdentificadorModel getTarjetaId() {
        return tarjetaId;
    }

    public void setTarjetaId(IdentificadorModel id) {
        this.tarjetaId = id;
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
