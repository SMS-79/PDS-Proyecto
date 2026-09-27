package inf.pds.proy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TableroModel {

    private IdentificadorModel id;
    private String nombre;
    private List<ListaTareaModel> listas = new ArrayList<>();

    public TableroModel() {
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

    public List<ListaTareaModel> getListas() {
        return listas;
    }

    public void setListas(List<ListaTareaModel> listas) {
        this.listas = listas;
    }
}
