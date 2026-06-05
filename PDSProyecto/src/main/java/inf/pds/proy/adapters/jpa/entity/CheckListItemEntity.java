package inf.pds.proy.adapters.jpa.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="checklist_items")
public class CheckListItemEntity {

	private Long id;
    private String descripcion;
    private boolean completado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isCompletado() {
		return completado;
	}
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	@Override
	public int hashCode() {
		return Objects.hash(completado, descripcion, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CheckListItemEntity))
			return false;
		CheckListItemEntity other = (CheckListItemEntity) obj;
		return completado == other.completado && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(id, other.id);
	}
    
    
	
}
