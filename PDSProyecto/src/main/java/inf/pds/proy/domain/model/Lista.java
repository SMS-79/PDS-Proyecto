package inf.pds.proy.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lista {
	
	private UUID id; 
	private String tipo; 
	private List<Tarjeta> tarjetas; 
	
	public Lista (String tipo) {
		this.id = UUID.randomUUID(); 
		this.tipo = tipo;
		this.tarjetas = new ArrayList<>(); 
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
