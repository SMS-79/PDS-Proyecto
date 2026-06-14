package inf.pds.proy.adapters.jpa.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("TAREA") 
public class TarjetaTareaEntity extends TarjetaEntity {
	
	
	private String descripcion; 
	private LocalDate fechaLimite; 
	
	@ManyToOne
	@JoinColumn(name="responsable_id", nullable=true)
	private UsuarioEntity responsable;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public UsuarioEntity getReponsable() {
		return responsable;
	}
	public void setReponsable(UsuarioEntity reponsable) {
		this.responsable = reponsable;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fechaLimite, responsable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TarjetaTareaEntity))
			return false;
		TarjetaTareaEntity other = (TarjetaTareaEntity) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fechaLimite, other.fechaLimite)
				&& Objects.equals(responsable, other.responsable);
	} 
	
	
	

}
