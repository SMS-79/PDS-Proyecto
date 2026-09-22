package inf.pds.proy.adapters.rest.dto;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tarjeta;
import jakarta.validation.constraints.*;

public class ListaTareasDTO {
    private Long id; 

    @NotNull(message = "El tablero de la lista de tareas no puede ser nulo")
    private Tablero tablero; 

    @NotBlank(message = "El tipo de la lista de tareas no puede estar vacío")
    private String tipo; 

    private List<Tarjeta> tarjetas; 

    @Positive (message = "El límite de items debe ser un número positivo")
    private int limiteItems;

    private List<ListaTareas> caminoRequerido;

    public ListaTareasDTO() {
    }

    public ListaTareasDTO(Long id, Tablero tablero, String tipo, int limiteItems) {
        this.id = id;
        this.tablero = tablero;
        this.tipo = tipo;
        this.tarjetas = new ArrayList<>();
        this.limiteItems = limiteItems;
        this.caminoRequerido = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public int getLimiteItems() {
        return limiteItems;
    }

    public void setLimiteItems(int limiteItems) {
        this.limiteItems = limiteItems;
    }

    public List<ListaTareas> getCaminoRequerido() {
        return caminoRequerido;
    }

    public void setCaminoRequerido(List<ListaTareas> caminoRequerido) {
        this.caminoRequerido = caminoRequerido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(caminoRequerido, id, limiteItems, tablero, tarjetas, tipo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ListaTareasDTO))
            return false;
        ListaTareasDTO other = (ListaTareasDTO) obj;
        return Objects.equals(caminoRequerido, other.caminoRequerido) &&
                Objects.equals(id, other.id) &&
                limiteItems == other.limiteItems &&
                Objects.equals(tablero, other.tablero) &&
                Objects.equals(tarjetas, other.tarjetas) &&
                Objects.equals(tipo, other.tipo);
    }

    @Override
    public String toString() {
        return "ListaTareasDTO [id=" + id + ", tablero=" + tablero + ", tipo=" + tipo + ", tarjetas=" + tarjetas
                + ", limiteItems=" + limiteItems + ", caminoRequerido=" + caminoRequerido + "]";
    }

    
    
    
}
