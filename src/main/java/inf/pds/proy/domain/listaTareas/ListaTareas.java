package inf.pds.proy.domain.listaTareas;

import java.util.List;
import java.util.ArrayList;

import inf.pds.proy.domain.listaTareas.tarjetas.Tarjeta;

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

	public List<Tarjeta> getTargeta(){
		return tarjetas;
	}

	public String getTitulo(){
		return titulo; 
	}
	
}
