package inf.pds.proy.adapters.jpa.entity;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TAREA") 
public class TarjetaTareaEntity extends TarjetaEntity {
	
	
	private String descripcion; 
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descripcion);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TarjetaTareaEntity))
			return false;
		TarjetaTareaEntity other = (TarjetaTareaEntity) obj;
		return Objects.equals(descripcion, other.descripcion);
	}

	
	
	
	

}
