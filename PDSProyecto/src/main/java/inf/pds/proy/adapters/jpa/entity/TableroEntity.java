package inf.pds.proy.adapters.jpa.entity;

import java.net.URL;
import java.util.List;

import inf.pds.proy.domain.model.HistorialOps;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.TableroId;
import inf.pds.proy.domain.model.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name="tableros")
public class TableroEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private TableroId id;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="propietario_id", nullable=false)
	private Usuario propietario; 
	
	@Column(unique = true, nullable = false)
	private URL url; 
	
	private boolean bloqueado;
	private List<HistorialOps> historialOp;
	
	@ManyToOne
	@JoinColumn(name="miembros", nullable=true)
	private List<Usuario> miembros; 
	private List<ListaTareas> listaTareas;
	private ListaTareas completedList;
	public TableroId getId() {
		return id;
	}
	public void setId(TableroId id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Usuario getPropietario() {
		return propietario;
	}
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public List<HistorialOps> getHistorialOp() {
		return historialOp;
	}
	public void setHistorialOp(List<HistorialOps> historialOp) {
		this.historialOp = historialOp;
	}
	public List<Usuario> getMiembros() {
		return miembros;
	}
	public void setMiembros(List<Usuario> miembros) {
		this.miembros = miembros;
	}
	public List<ListaTareas> getListaTareas() {
		return listaTareas;
	}
	public void setListaTareas(List<ListaTareas> listaTareas) {
		this.listaTareas = listaTareas;
	}
	public ListaTareas getCompletedList() {
		return completedList;
	}
	public void setCompletedList(ListaTareas completedList) {
		this.completedList = completedList;
	}

	
	
	
}
