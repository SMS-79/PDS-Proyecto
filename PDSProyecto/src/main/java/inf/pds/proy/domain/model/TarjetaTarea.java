package inf.pds.proy.domain.model;

import java.time.LocalDate;

import inf.pds.proy.domain.model.ids.TarjetaId;
import inf.pds.proy.domain.model.ids.UsuarioId;

public class TarjetaTarea extends Tarjeta {

	private String descripcion; 
    
	public TarjetaTarea(TarjetaId id, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, UsuarioId responsable, String descripcion) {
		super(id, nombre, etiqueta, fechaLimite, responsable);
		this.descripcion = descripcion;
	}
	
	public TarjetaTarea(TarjetaId id, String nombre, LocalDate fechaLimite, UsuarioId responsable, String descripcion) {
		this(id, nombre, null, fechaLimite, responsable, descripcion);
	}
	
	public TarjetaTarea(String nombre, Etiqueta etiqueta, LocalDate fechaLimite, UsuarioId responsable, String descripcion) {
		this(TarjetaId.random(), nombre, etiqueta, fechaLimite, responsable, descripcion);
	}
	
	public TarjetaTarea(String nombre, LocalDate fechaLimite, UsuarioId responsable, String descripcion) {
		this(TarjetaId.random(), nombre, null, fechaLimite, responsable, descripcion);
	}
   
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
}
