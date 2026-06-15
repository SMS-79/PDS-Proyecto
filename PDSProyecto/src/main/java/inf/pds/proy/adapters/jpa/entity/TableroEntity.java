package inf.pds.proy.adapters.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	private LocalDateTime bloqueoFin;

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
	private ListaTareasEntity listaCompletadas;
	
	
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
	public LocalDateTime getBloqueoFin() {
		return this.bloqueoFin;
	}
	public void setBloqueoFin(LocalDateTime bloqueoFin) {
		this.bloqueoFin = bloqueoFin;
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
	public ListaTareasEntity getListaCompletadas() {
		return listaCompletadas;
	}
	public void setListaCompletadas(ListaTareasEntity listaCompletadas) {
		this.listaCompletadas = listaCompletadas;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bloqueado, listaCompletadas, historialOp, id, listaTareas, miembros, nombre, propietario, url);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TableroEntity))
			return false;
		TableroEntity other = (TableroEntity) obj;
		return bloqueado == other.bloqueado && Objects.equals(listaCompletadas, other.listaCompletadas)
				&& Objects.equals(historialOp, other.historialOp) && Objects.equals(id, other.id)
				&& Objects.equals(listaTareas, other.listaTareas) && Objects.equals(miembros, other.miembros)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(propietario, other.propietario)
				&& Objects.equals(url, other.url);
	}
	

	
	
	
}
