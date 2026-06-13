package inf.pds.proy.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.model.ids.ListaTareasId.IdentificadorListaException;

public class Tablero {
	
	
	
	private TableroId id;
	private String nombre;
	private Usuario propietario; 
	private String url; 
	private boolean bloqueado; // bloqueo temporal de un tablero que durará como máximo una semana
	private List<HistorialOps> historialOp;
	private List<Usuario> miembros; 
	private List<ListaTareas> listasTareas; // columnas dinámicas tipo (DOING, T0DO, BACKLOG, STOPPED etc...) 
	private ListaTareas completedList; // lista para separar las completadas
	
	public Tablero() {
		
	}
	
	public Tablero(TableroId id, String nombre, Usuario propietario, String url) {
		this.id = id;
		this.nombre = nombre;
		this.propietario = propietario;
		this.url = url;
		this.bloqueado = false;
		this.miembros = new ArrayList<>();
		this.listasTareas = new ArrayList<>();
		this.historialOp = new ArrayList<>();
		try {
			this.completedList = new ListaTareas("Completadas");
		} catch (IdentificadorListaException e) {
			e.printStackTrace();
		}
	}

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


	public List<HistorialOps> getHistorialOp() {
		return historialOp;
	}
	
	public void setHistorialOp(List<HistorialOps> historial) {
		this.historialOp = historial;
	}

	public List<ListaTareas> getListas() {
		return listasTareas;
	}
	
	public void setListas(List<ListaTareas> listaTareas) {
		this.listasTareas = listaTareas;
	}


	public ListaTareas getCompletedList() {
		return completedList;
	}
	
	public void setCompletedList(ListaTareas listaCompletadas) {
		this.completedList = listaCompletadas;
	}
	
	public List<Usuario> getMiembros(){
		return miembros;
	}
	
	public ListaTareas crearLista(String tipo) {
		try {
			ListaTareas listaTareas = new ListaTareas(tipo);
			this.listasTareas.add(listaTareas);
			return listaTareas;
		}catch(IdentificadorListaException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Optional<ListaTareas> obtenerLista(ListaTareasId id) {
		return this.listasTareas.stream()
				.filter(l -> l.getId().equals(id))
				.findFirst();
	}
	
	public void eliminarLista(ListaTareas lista) {
		this.listasTareas.remove(lista);
	}
	
	public void addMiembro(Usuario u) {
		this.miembros.add(u); 
	}
	
	public void registrarOp(HistorialOps op) {
		this.historialOp.add(op); 
	}

	
	
	public void addTarjeta(ListaTareasId idLista, Tarjeta tarjeta, Usuario usuario) {
		if(this.bloqueado) {
			throw new IllegalStateException("El tablero está bloqueado, no se pueden añadir tarjetas");
		}
		
		ListaTareas lista = listasTareas.stream()
			.filter(l -> l.getId().equals(idLista))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Lista no encotrada"));
				
		lista.addTarjeta(tarjeta);
		
		registrarOp(new HistorialOps(TipoOperacion.TARJETA_CREADA, usuario));
	}
	
	

}
