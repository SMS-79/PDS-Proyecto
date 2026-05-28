package inf.pds.proy.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistorialOps {
	
	private UUID id; 
	private String descripcion; 
	LocalDateTime fecha; 
	private Usuario usuario; 
	
	public HistorialOps() {}
	
	public HistorialOps(String descripcion, Usuario usuario) {
		this.id = UUID.randomUUID(); 
		this.descripcion = descripcion; 
		this.usuario = usuario; 
		this.fecha = LocalDateTime.now(); 
	}
	
	public UUID id() {
		return id; 
	}
	
	public String getDescripcion() {
		return descripcion; 
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public Usuario getUsuario() {
		return usuario; 
	}
}
