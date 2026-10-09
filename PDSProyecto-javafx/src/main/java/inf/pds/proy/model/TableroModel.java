package inf.pds.proy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TableroModel {

    private IdentificadorModel tableroId;
    private String nombre;
    private List<ListaTareaModel> listas = new ArrayList<>();
    private UsuarioModel propietario;

    public TableroModel() {
    }

    public IdentificadorModel getTableroId() {
        return tableroId;
    }

    public void setTableroId(IdentificadorModel id) {
        this.tableroId = id;
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

    public UsuarioModel getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioModel propietario) {
        this.propietario = propietario;
    }

    
}
