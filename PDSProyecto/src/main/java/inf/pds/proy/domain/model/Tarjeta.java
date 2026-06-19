package inf.pds.proy.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.ids.TarjetaId;

public abstract class Tarjeta {

	private TarjetaId id;
	private String nombre;
	private Optional<Etiqueta> etiqueta;
	private LocalDate fechaLimite; 
	private Usuario responsable; 
	private boolean completada;
	private List<ListaTareas> historialLista = new ArrayList<>();
	
	
	protected Tarjeta(TarjetaId id, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) {
		this.id = id;
		this.nombre = nombre;
		this.etiqueta = Optional.ofNullable(etiqueta);
		this.fechaLimite = fechaLimite;
		this.responsable = responsable;
		this.completada = false;
	}
	
	protected Tarjeta(TarjetaId id, String nombre, LocalDate fechaLimite, Usuario responsable) {
		this(id, nombre, null, fechaLimite, responsable);
	}
	
	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Usuario getResponsable() {
		return responsable;
	}

	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

	public void setEtiqueta(Optional<Etiqueta> etiqueta) {
		this.etiqueta = etiqueta;
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

	public List<ListaTareas> getHistorialLista() {
		return historialLista;
	}

	public void setHistorialLista(List<ListaTareas> historialLista) {
		this.historialLista = historialLista;
	}
	
	public void addListaToHistorial(ListaTareas lista) {
		this.historialLista.add(lista);
	}
	
	
	
	
	
	
	
	
}
