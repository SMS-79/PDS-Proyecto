package inf.pds.proy.domain.model;

import java.util.ArrayList;
import java.util.List;

import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.ListaTareasId.IdentificadorListaException;
import inf.pds.proy.domain.model.exceptions.*;

public class ListaTareas {
	
	private ListaTareasId id; 
	private String tipo; 
	private List<Tarjeta> tarjetas; 
	private int limiteItems;
	private List<ListaTareas> caminoRequerido;
	
	public ListaTareas (String tipo) throws IdentificadorListaException {
		this.id = ListaTareasId.random();
		this.tipo = tipo;
		this.tarjetas = new ArrayList<>(); 
		this.limiteItems = -1;
		this.caminoRequerido = null;
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
	
	public void addTarjeta(Tarjeta t){ // controlar desde servicio si tablero está bloqueado, si lo está esta función no podrá llevarse a cabo
		this.tarjetas.add(t);
	}
	
	public boolean canAdd(Tarjeta t) {
		return (this.limiteItems == -1 || this.limiteItems > this.tarjetas.size()) && (this.caminoRequerido == null || this.caminoRequerido.equals(t.getHistorialLista()));
	}
	
	public void removeTarjeta(Tarjeta t) {
		this.tarjetas.remove(t); 
	}
	
	public boolean tarjetasEmpty() {
		return this.tarjetas.isEmpty(); 
	}

	public int getLimiteItems() {
		return limiteItems;
	}

	public void setLimiteItems(int limiteItems) {
		this.limiteItems = limiteItems;
	}

	public List<ListaTareas> getCaminoRequerido() {
		return caminoRequerido;
	}

	public void setCaminoRequerido(List<ListaTareas> caminoRequerido) {
		this.caminoRequerido = caminoRequerido;
	}
	
	public void addCaminoRequerido(List<ListaTareas> caminoRequerido) {
		if(this.caminoRequerido == null) {
			caminoRequerido = new ArrayList<>();
		}
		this.caminoRequerido.addAll(caminoRequerido);
	}
	
	
	
}
