package inf.pds.proy.domain.model;

import java.util.UUID;

public class CheckListItem {

	private UUID id;
    private String descripcion;
    private boolean completado;

    public CheckListItem() {}

    public CheckListItem(String descripcion) {
        this.id = UUID.randomUUID();
        this.descripcion = descripcion;
        this.completado = false;
    }
   
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
