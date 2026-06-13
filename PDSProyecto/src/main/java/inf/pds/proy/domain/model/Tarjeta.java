package inf.pds.proy.domain.model;

import java.util.Optional;

import inf.pds.proy.domain.model.ids.TarjetaId;

public abstract class Tarjeta {

	
	private TarjetaId id;
	private String nombre;
	private Optional<Etiqueta> etiqueta;
	private boolean completada;
	
	
	protected Tarjeta(TarjetaId id, String nombre, Etiqueta etiqueta) {
		this.id = id;
		this.nombre = nombre;
		this.etiqueta = Optional.ofNullable(etiqueta);
		this.completada = false;
	}
	
	protected Tarjeta(TarjetaId id, String nombre) {
		this(id, nombre, null);
	}

	public TarjetaId getId() {
		return id;
	}


	public void setId(TarjetaId id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Optional<Etiqueta> getEtiqueta() {
		return etiqueta;
	}


	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = Optional.of(etiqueta);
	}


	public boolean isCompletada() {
		return completada;
	}


	public void setCompletada(boolean completada) {
		this.completada = completada;
	}
	
	
	
	
	
	
}
