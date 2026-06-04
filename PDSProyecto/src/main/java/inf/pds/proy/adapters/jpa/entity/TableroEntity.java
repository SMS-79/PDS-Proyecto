package inf.pds.proy.adapters.jpa.entity;

import java.net.URL;
import java.util.List;

import inf.pds.proy.domain.model.HistorialOps;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.TableroId;
import jakarta.persistence.*;

@Entity
@Table(name="tableros")
public class TableroEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="propietario_id", nullable=false)
	private UsuarioEntity propietario; 
	
	@Column(unique = true, nullable = false)
	private String url; 
	
	private boolean bloqueado;

    @OneToMany
    @JoinColumn(name = "tablero_id")
    private List<HistorialOpsEntity> historialOp; 
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tablero_id")
    private List<ListaTareasEntity> listaTareas;
	
	@ManyToMany
	@JoinTable(name = "tablero_miembros",
	joinColumns = @JoinColumn(name = "tablero_id"),
	inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<UsuarioEntity> miembros; 
	private ListaTareas completedList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public UsuarioEntity getPropietario() {
		return propietario;
	}
	public void setPropietario(UsuarioEntity propietario) {
		this.propietario = propietario;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public List<HistorialOpsEntity> getHistorialOp() {
		return historialOp;
	}
	public void setHistorialOp(List<HistorialOpsEntity> historialOp) {
		this.historialOp = historialOp;
	}
	public List<UsuarioEntity> getMiembros() {
		return miembros;
	}
	public void setMiembros(List<UsuarioEntity> miembros) {
		this.miembros = miembros;
	}
	public List<ListaTareasEntity> getListaTareas() {
		return listaTareas;
	}
	public void setListaTareas(List<ListaTareasEntity> listaTareas) {
		this.listaTareas = listaTareas;
	}
	public ListaTareas getCompletedList() {
		return completedList;
	}
	public void setCompletedList(ListaTareas completedList) {
		this.completedList = completedList;
	}

	
	
	
}
