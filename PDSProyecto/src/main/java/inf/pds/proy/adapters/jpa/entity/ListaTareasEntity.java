package inf.pds.proy.adapters.jpa.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lista_tareas")
public class ListaTareasEntity {

    @Id
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "tablero_id")
    private TableroEntity tablero; 

    private String tipo; 

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lista_tareas_id") 
    private List<TarjetaEntity> tarjetas = new ArrayList<>(); 

    @Column(name = "limite_items", nullable = false)
    private int limiteItems;

    @ManyToMany
    @JoinTable(
        name = "lista_tareas_caminos",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "camino_requerido_id")
    )
    private List<ListaTareasEntity> caminoRequerido = new ArrayList<>();

    public ListaTareasEntity() {}

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getTipo() { 
        return tipo; 
    }
    
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }

    public TableroEntity getTablero() { 
        return tablero; 
    }
    
    public void setTablero(TableroEntity tablero) { 
        this.tablero = tablero; 
    }

    public List<TarjetaEntity> getTarjetas() { 
        return tarjetas; 
    }
    
    // Aseguramos mutabilidad
    public void setTarjetas(List<TarjetaEntity> tarjetas) { 
        this.tarjetas = tarjetas == null ? new ArrayList<>() : new ArrayList<>(tarjetas);
    }

    public int getLimiteItems() {
        return limiteItems;
    }

    public void setLimiteItems(int limiteItems) {
        this.limiteItems = limiteItems;
    }

    public List<ListaTareasEntity> getCaminoRequerido() {
        return caminoRequerido;
    }

    // Aseguramos mutabilidad
    public void setCaminoRequerido(List<ListaTareasEntity> caminoRequerido) {
        this.caminoRequerido = caminoRequerido == null ? new ArrayList<>() : new ArrayList<>(caminoRequerido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caminoRequerido, id, limiteItems, tablero, tarjetas, tipo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ListaTareasEntity))
            return false;
        ListaTareasEntity other = (ListaTareasEntity) obj;
        return Objects.equals(caminoRequerido, other.caminoRequerido) && Objects.equals(id, other.id)
                && limiteItems == other.limiteItems && Objects.equals(tablero, other.tablero)
                && Objects.equals(tarjetas, other.tarjetas) && Objects.equals(tipo, other.tipo);
    }
}