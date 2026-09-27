package inf.pds.proy.domain.model;

import java.time.LocalDateTime;

import inf.pds.proy.domain.model.ids.HistorialOpsId;
import inf.pds.proy.domain.model.ids.UsuarioId;

public class HistorialOps {
	
	public enum TipoOperacion {
		LISTA_CREADA, LISTA_BUSCADA, LISTAS_OBTENIDAS, LISTA_ELIMINADA, TARJETA_CREADA, TARJETA_BUSCADA, TARJETAS_OBTENIDAS, TARJETA_ELIMINADA, TARJETA_COMPLETADA, TARJETA_DESPLAZADA, AÑADIR_MIEMBRO, ELIMINAR_MIEMBRO, TABLERO_BLOQUEADO, TABLERO_DESBLOQUEADO
	}
	
	private HistorialOpsId id;
	private String descripcion;
	private TipoOperacion tipo;
	private LocalDateTime fecha;
	private UsuarioId usuario;
	
	public HistorialOps() {}
	
	public HistorialOps(HistorialOpsId id, String descripcion, TipoOperacion tipo, UsuarioId usuario, LocalDateTime fecha){
		this.id = id;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.usuario = usuario;
		this.fecha = fecha;
	}
	
	public HistorialOps(TipoOperacion tipo, String descripcion, UsuarioId usuario){
		this(HistorialOpsId.random(), descripcion, tipo, usuario, LocalDateTime.now());
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
	
	public UsuarioId getUsuario() {
		return usuario; 
	}

	public void setUsuario(UsuarioId usuario) {
		this.usuario = usuario;
	}

}
