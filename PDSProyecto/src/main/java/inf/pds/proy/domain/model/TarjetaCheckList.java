package inf.pds.proy.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import inf.pds.proy.domain.model.ids.TarjetaId;
public class TarjetaCheckList extends Tarjeta{
	
	List<CheckListItem> items;

	public TarjetaCheckList(TarjetaId id, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) {
		super(id, nombre, etiqueta, fechaLimite, responsable);
		this.items = new ArrayList<>();
	}
	
	public TarjetaCheckList(TarjetaId id, String nombre, LocalDate fechaLimite, Usuario responsable, String descripcion) {
		this(id, nombre, null, fechaLimite, responsable);
	}
	
	public TarjetaCheckList(String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) {
		this(TarjetaId.random(), nombre, etiqueta, fechaLimite, responsable);
	}
	
	public TarjetaCheckList(String nombre, LocalDate fechaLimite, Usuario responsable) {
		this(TarjetaId.random(), nombre, null, fechaLimite, responsable);
	}
   

    public List<CheckListItem> getItems() { 
    	return items; 
    }
    
    public void setItems(List<CheckListItem> items) { 
    	this.items = items; 
    }
    

    public void addItem(CheckListItem item) { 
    	this.items.add(item); 
    }
    
    public void removeItem(CheckListItem item) { 
    	this.items.remove(item); 
    }
    
    public boolean todosCompletados() { // funcion de autocompletado si la tarjeta tiene todos los checklists marcados
        return !items.isEmpty() && items.stream().allMatch(CheckListItem::isCompletado);
    }
  
}
