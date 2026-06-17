package inf.pds.proy.adapters.jpa.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarjetas")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_tarjeta", discriminatorType = DiscriminatorType.STRING)
public abstract class TarjetaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String etiquetaNombre;
	private String etiquetaColor;
	
	private LocalDate fechaLimite; 
	
	@ManyToOne
	@JoinColumn(name="responsable_id", nullable=true)
	private UsuarioEntity responsable;
	
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

	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public UsuarioEntity getResponsable() {
		return responsable;
	}

	public void setResponsable(UsuarioEntity responsable) {
		this.responsable = responsable;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completada, etiquetaColor, etiquetaNombre, fechaLimite, id, nombre, responsable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TarjetaEntity))
			return false;
		TarjetaEntity other = (TarjetaEntity) obj;
		return completada == other.completada && Objects.equals(etiquetaColor, other.etiquetaColor)
				&& Objects.equals(etiquetaNombre, other.etiquetaNombre)
				&& Objects.equals(fechaLimite, other.fechaLimite) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(responsable, other.responsable);
	}
	
	
	
	
	
	
}
