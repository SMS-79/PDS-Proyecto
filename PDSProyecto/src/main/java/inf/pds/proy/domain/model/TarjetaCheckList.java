package inf.pds.proy.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TarjetaCheckList extends Tarjeta{
	
	List<CheckListItem> items;

	public TarjetaCheckList(Long id, String nombre, Etiqueta etiqueta) {
		super(id, nombre, etiqueta);
		this.items = new ArrayList<>(); 
	}
	
    public TarjetaCheckList(String nombre, Etiqueta etiqueta) {
        super(new Random().nextLong(), nombre, etiqueta);
        this.items = new ArrayList<>();
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
