package inf.pds.proy.domain.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tablero {
	
	
	
	private UUID id;
	private String nombre;
	private Usuario propietario; 
	private URL url; 
	private boolean bloqueado; // bloqueo temporal de un tablero que durará como máximo una semana
	private List<HistorialOps> historialOp;
	private List<Usuario> miembros; 
	private List<Lista> listas; // columnas dinámicas tipo (DOING, TODO, BACKLOG, STOPPED etc...) 
	private Lista completedList; // lista para separar las completadas
	
	public Tablero() {
		
	}
	
	public Tablero(String nombre, Usuario propietario, URL url) {
		this.id = UUID.randomUUID(); 
		this.nombre = nombre; 
		this.propietario = propietario; 
		this.url = url; 
		this.bloqueado = false;
		this.miembros = new ArrayList<>(); 
		this.listas = new ArrayList<>(); 
		this.historialOp = new ArrayList<>(); 
		this.completedList = new Lista("Completadas"); 
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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


	public List<Lista> getListas() {
		return listas;
	}


	public Lista getCompletedList() {
		return completedList;
	}
	
	public void addLista(Lista lista) {
		this.listas.add(lista);
	}
	
	public void addMiembro(Usuario u) {
		this.miembros.add(u); 
	}
	
	public void registrarOp(HistorialOps op) {
		this.historialOp.add(op); 
	}

	
	

}
