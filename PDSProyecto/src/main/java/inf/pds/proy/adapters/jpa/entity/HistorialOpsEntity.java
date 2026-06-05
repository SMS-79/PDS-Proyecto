package inf.pds.proy.adapters.jpa.entity;

import java.time.LocalDateTime;

import inf.pds.proy.domain.model.TipoOperacion;
import jakarta.persistence.*;

@Entity
@Table(name="historial")
public class HistorialOpsEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String descripcion;
	private TipoOperacion tipo;
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private UsuarioEntity usuario;

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

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	
	
}
   