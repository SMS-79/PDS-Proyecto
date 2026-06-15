package inf.pds.proy.domain.model;

import java.time.LocalDate;

import inf.pds.proy.domain.model.ids.TarjetaId;

public class TarjetaTarea extends Tarjeta {
	
	private String descripcion; 
	private LocalDate fechaLimite; 
	private Usuario responsable; 
	
	public TarjetaTarea(TarjetaId id, String nombre, Etiqueta etiqueta) {
		super(id, nombre, etiqueta);
	}

    public TarjetaTarea(String nombre, Usuario responsable, Etiqueta etiqueta, LocalDate fechaLimite) {
        super(TarjetaId.random(), nombre, etiqueta);
        this.responsable = responsable; 
        this.fechaLimite = fechaLimite; 
    }
    
   
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Usuario getReponsable() {
		return responsable;
	}

	public void setReponsable(Usuario responsable) {
		this.responsable = responsable;
	}
    
    
}
