package inf.pds.proy.domain.model;

import inf.pds.proy.domain.model.ids.CheckListItemId;

public class CheckListItem {

	private CheckListItemId id;
    private String descripcion;
    private boolean completado;

    public CheckListItem() {}
    
    public CheckListItem(CheckListItemId id, String descripcion, boolean completado) {
        this.id = id;
        this.descripcion = descripcion;
        this.completado = completado;
    }

    public CheckListItem(String descripcion) {
    	this(CheckListItemId.random(), descripcion, false);
    }
   
    public CheckListItemId getId() {
		return id;
	}

	public void setId(CheckListItemId id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isCompletado() { 
    	return completado; 
    }
    
    public void setCompletado(boolean completado) { 
    	this.completado = completado; 
    }
}
