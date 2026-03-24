package inf.pds.proy.domain.tablero.listaTareas;

import java.util.List;

import inf.pds.proy.domain.tablero.listaTareas.tarjetas.Tarjeta;

import java.util.ArrayList;

public class ListaTareas {

	private String titulo; 
	private List<Tarjeta> tarjetas;

	public ListaTareas(String titulo){
		this.titulo = titulo; 
		this.tarjetas = new ArrayList<>(); 
	}

	public void addTarjeta(Tarjeta tarjeta){
		this.tarjetas.add(tarjeta);
	}

	public List<Tarjeta> getTarjetas(){
		return tarjetas;
	}

	public String getTitulo(){
		return titulo; 
	}
	
}
