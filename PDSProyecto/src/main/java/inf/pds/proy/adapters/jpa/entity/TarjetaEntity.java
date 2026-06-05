package inf.pds.proy.adapters.jpa.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "tarjetas")
public abstract class TarjetaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String etiquetaNombre;
	private String etiquetaColor;
	private boolean completada;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEtiquetaNombre() {
		return etiquetaNombre;
	}
	public void setEtiquetaNombre(String etiquetaNombre) {
		this.etiquetaNombre = etiquetaNombre;
	}
	public String getEtiquetaColor() {
		return etiquetaColor;
	}
	public void setEtiquetaColor(String etiquetaColor) {
		this.etiquetaColor = etiquetaColor;
	}
	public boolean isCompletada() {
		return completada;
	}
	public void setCompletada(boolean completada) {
		this.completada = completada;
	}
	@Override
	public int hashCode() {
		return Objects.hash(completada, etiquetaColor, etiquetaNombre, id, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TarjetaEntity))
			return false;
		TarjetaEntity other = (TarjetaEntity) obj;
		return completada == other.completada && Objects.equals(etiquetaColor, other.etiquetaColor)
				&& Objects.equals(etiquetaNombre, other.etiquetaNombre) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}
	
	
	
	
}
