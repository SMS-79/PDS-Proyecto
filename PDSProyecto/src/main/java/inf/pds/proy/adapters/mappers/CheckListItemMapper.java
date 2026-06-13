package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.CheckListItemEntity;
import inf.pds.proy.domain.model.CheckListItem;
import inf.pds.proy.domain.model.ids.CheckListItemId;
import inf.pds.proy.domain.model.ids.CheckListItemId.IdentificadorItemException;

@Component
public class CheckListItemMapper {

	public CheckListItemEntity toEntity(CheckListItem item) {
		CheckListItemEntity itemEntity = new CheckListItemEntity();
		itemEntity.setId(item.getId().getId());
		itemEntity.setDescripcion(item.getDescripcion());
		itemEntity.setCompletado(item.isCompletado());
		
		return itemEntity;
	}
	
	public CheckListItem toDomain(CheckListItemEntity itemEntity) {
		try {
			return new CheckListItem(CheckListItemId.of(itemEntity.getId()), itemEntity.getDescripcion(), itemEntity.isCompletado());
		}catch(IdentificadorItemException e) {
			e.printStackTrace();
		}
		return null;
	}
}