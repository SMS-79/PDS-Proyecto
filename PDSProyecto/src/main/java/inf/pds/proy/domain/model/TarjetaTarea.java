package inf.pds.proy.domain.model;

import java.time.LocalDate;

public class TarjetaTarea extends Tarjeta {
	
	private String descripcion; 
	private LocalDate fechaLimite; 
	private Usuario responsable; 
	
	public TarjetaTarea(Long id, String nombre, Etiqueta etiqueta) {
		super(id, nombre, etiqueta);
	}

    public TarjetaTarea(Long id, String nombre, Etiqueta etiqueta, String descripcion, LocalDate fechaLimite) {
        super(id, nombre, etiqueta);
        this.descripcion = descripcion;
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
