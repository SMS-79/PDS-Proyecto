package inf.pds.proy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaTareaModel {
    
    private IdentificadorModel ListaId;
    private String tipo;
    private List<TarjetaModel> tarjetas = new ArrayList<>();

    public ListaTareaModel() {
    }

    public IdentificadorModel getListaId() {
        return ListaId;
    }

    public void setListaId(IdentificadorModel id) {
        this.ListaId = id;
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
