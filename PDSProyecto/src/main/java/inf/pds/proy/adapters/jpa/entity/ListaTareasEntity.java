package inf.pds.proy.adapters.jpa.entity;


import java.util.ArrayList;
import java.util.List;

import inf.pds.proy.domain.model.ListaTareasId;
import inf.pds.proy.domain.model.TableroId;
import jakarta.persistence.*;


@Entity
@Table(name = "lista_tareas")
public class ListaTareasEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id; 
	
	@ManyToOne
	@JoinColumn(name = "tablero_id")
	private TableroEntity tablero; 
	
	private String tipo; 
	
	
	@OneToMany(mappedBy = "lista_tareas")
	@JoinColumn(name = "tarjetas")
	private List<TarjetaEntity> tarjetas; 
	
	
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
    
    public void setTarjetas(List<TarjetaEntity> tarjetas) { 
    	this.tarjetas = tarjetas;
    }
	
}
