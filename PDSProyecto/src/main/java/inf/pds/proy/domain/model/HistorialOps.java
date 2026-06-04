package inf.pds.proy.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistorialOps {
	
	private UUID id;
	private String descripcion;
	private TipoOperacion tipo;
	private LocalDateTime fecha;
	private Usuario usuario;
	
	public HistorialOps() {}
	
	public HistorialOps(TipoOperacion tipo, Usuario usuario){
		this.id = UUID.randomUUID();
		this.tipo = tipo;
		this.usuario = usuario;
		this.fecha = LocalDateTime.now();
	}
	
	public UUID id() {
		return id; 
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public TipoOperacion getTipo() {
		return tipo; 
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public Usuario getUsuario() {
		return usuario; 
	}
}
