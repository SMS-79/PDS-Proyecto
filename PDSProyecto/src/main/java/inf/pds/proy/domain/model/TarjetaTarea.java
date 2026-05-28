package inf.pds.proy.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class TarjetaTarea extends Tarjeta {
	
	private String descripcion; 
	private LocalDate fechaLimite; 
	private Usuario reponsable; 
	
	public TarjetaTarea(UUID id, String nombre, Etiqueta etiqueta) {
		super(id, nombre, etiqueta);
	}

    public TarjetaTarea(String nombre, Etiqueta etiqueta, String descripcion, LocalDate fechaLimite) {
        super(UUID.randomUUID(), nombre, etiqueta);
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
		return reponsable;
	}

	public void setReponsable(Usuario reponsable) {
		this.reponsable = reponsable;
	}
    
    
}
