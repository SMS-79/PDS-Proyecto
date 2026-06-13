package inf.pds.proy.domain.model;

import java.time.LocalDateTime;

import inf.pds.proy.domain.model.ids.HistorialOpsId;

public class HistorialOps {
	
	private HistorialOpsId id;
	private String descripcion;
	private TipoOperacion tipo;
	private LocalDateTime fecha;
	private Usuario usuario;
	
	public HistorialOps() {}
	
	public HistorialOps(HistorialOpsId id, TipoOperacion tipo, Usuario usuario, LocalDateTime fecha){
		this.id = id;
		this.tipo = tipo;
		this.usuario = usuario;
		this.fecha = fecha;
	}
	
	public HistorialOps(TipoOperacion tipo, Usuario usuario){
		this(HistorialOpsId.random(), tipo, usuario, LocalDateTime.now());
	}
	
	public HistorialOpsId getId() {
		return id; 
	}
	public void setId(HistorialOpsId id) {
		this.id = id;
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
	
	public void setTipo(TipoOperacion tipo) {
		this.tipo = tipo;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public Usuario getUsuario() {
		return usuario; 
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
