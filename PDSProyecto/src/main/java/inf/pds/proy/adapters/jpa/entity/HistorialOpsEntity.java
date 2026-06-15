package inf.pds.proy.adapters.jpa.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import inf.pds.proy.domain.model.HistorialOps.TipoOperacion;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fecha, id, tipo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof HistorialOpsEntity))
			return false;
		HistorialOpsEntity other = (HistorialOpsEntity) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id) && tipo == other.tipo && Objects.equals(usuario, other.usuario);
	}
	
	
	
}
   