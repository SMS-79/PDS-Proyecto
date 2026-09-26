package inf.pds.proy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaTareaModel {
    
    private IdentificadorModel id;
    private String tipo;
    private List<TarjetaModel> tarjetas = new ArrayList<>();

    public ListaTareaModel() {
    }

    public IdentificadorModel getId() {
        return id;
    }

    public void setId(IdentificadorModel id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<TarjetaModel> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaModel> tarjetas) {
        this.tarjetas = tarjetas;
    }
}
