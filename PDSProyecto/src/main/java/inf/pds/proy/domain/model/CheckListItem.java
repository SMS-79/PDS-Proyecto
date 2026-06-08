package inf.pds.proy.domain.model;

import java.util.Random;

public class CheckListItem {

	private Long id;
    private String descripcion;
    private boolean completado;

    public CheckListItem() {}
    
    public CheckListItem(Long id, String descripcion, boolean completado) {
        this.id = id;
        this.descripcion = descripcion;
        this.completado = completado;
    }

    public CheckListItem(String descripcion) {
    	this(new Random().nextLong(), descripcion, false);
    }
   
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
