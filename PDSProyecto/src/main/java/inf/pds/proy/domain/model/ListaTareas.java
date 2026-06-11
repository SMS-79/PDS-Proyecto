package inf.pds.proy.domain.model;

import java.util.ArrayList;
import java.util.List;

import inf.pds.proy.domain.model.ListaTareasId.IdentificadorListaException;

public class ListaTareas {
	
	private ListaTareasId id; 
	private String tipo; 
	private List<Tarjeta> tarjetas; 
	
	public ListaTareas (String tipo) throws IdentificadorListaException {
		this.id = ListaTareasId.random();
		this.tipo = tipo;
		this.tarjetas = new ArrayList<>(); 
	}

	public ListaTareasId getId() {
		return id;
	}

	public void setId(ListaTareasId id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}
	
	public void addTarjeta(Tarjeta t) { // controlar desde servicio si tablero está bloqueado, si lo está esta función no podrá llevarse a cabo
		this.tarjetas.add(t); 
	}
	
	public void removeTarjeta(Tarjeta t) {
		this.tarjetas.remove(t); 
	}
	
	public boolean tarjetasEmpty() {
		return this.tarjetas.isEmpty(); 
	}
	
}
