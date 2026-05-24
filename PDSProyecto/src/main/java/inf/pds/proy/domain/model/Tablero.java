package inf.pds.proy.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tablero {

	
	private String nombre; 
	private UUID id;  		
	private List<Lista> listas; 	
	private boolean bloqueado; 
	
	public Tablero(String nombre) {
		this.id = UUID.randomUUID(); 
		this.nombre = nombre; 
		this.listas = new ArrayList<>(); 
		this.bloqueado = false; 
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<Lista> getListas() {
		return listas;
	}

	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	
	
}
