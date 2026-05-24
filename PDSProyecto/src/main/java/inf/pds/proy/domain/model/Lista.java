package inf.pds.proy.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lista {

	private UUID id; 
	private String titulo; 
	private List<Tarjeta> tarjetas; 
	private boolean completada; 
	
	public Lista(String titulo, boolean completada) {
		this.id = UUID.randomUUID(); 
		this.titulo = titulo; 
		this.tarjetas = new ArrayList<>(); 
		this.completada = completada; 	
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}
	
	
	
}
