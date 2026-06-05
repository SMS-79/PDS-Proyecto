package inf.pds.proy.adapters.jpa.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CHECKLIST")
public class TarjetaCheckListEntity extends TarjetaEntity {
	
	
	List<CheckListItemEntity> items;

	public List<CheckListItemEntity> getItems() {
		return items;
	}

	public void setItems(List<CheckListItemEntity> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(items);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TarjetaCheckListEntity))
			return false;
		TarjetaCheckListEntity other = (TarjetaCheckListEntity) obj;
		return Objects.equals(items, other.items);
	}

	
	
	

}
