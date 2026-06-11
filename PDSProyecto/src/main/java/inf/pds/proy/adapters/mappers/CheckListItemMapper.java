package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.CheckListItemEntity;
import inf.pds.proy.domain.model.CheckListItem;

@Component
public class CheckListItemMapper {

	public CheckListItemEntity toEntity(CheckListItem item) {
		CheckListItemEntity itemEntity = new CheckListItemEntity();
		itemEntity.setId(item.getId());
		itemEntity.setDescripcion(item.getDescripcion());
		itemEntity.setCompletado(item.isCompletado());
		
		return itemEntity;
	}
	
	public CheckListItem toDomain(CheckListItemEntity itemEntity) {
		return new CheckListItem(itemEntity.getId(), itemEntity.getDescripcion(), itemEntity.isCompletado());
	}
}