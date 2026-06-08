package inf.pds.proy.adapters.mappers;

import inf.pds.proy.adapters.jpa.entity.TarjetaCheckListEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaTareaEntity;
import inf.pds.proy.domain.model.Etiqueta;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.TarjetaCheckList;
import inf.pds.proy.domain.model.TarjetaTarea;

public class TarjetaMapper {
	
	CheckListItemMapper itemMapper;

	public TarjetaEntity toEntity(Tarjeta tarjeta) {
		TarjetaEntity tarjetaEntity;
		if(tarjeta instanceof TarjetaCheckList) {
			TarjetaCheckListEntity checkListEntity = new TarjetaCheckListEntity();
			checkListEntity.setItems(((TarjetaCheckList) tarjeta).getItems().stream()
																			.map(itemMapper::toEntity)
																			.toList());
			tarjetaEntity = checkListEntity;
		}
		else{
			TarjetaTareaEntity taskEntity = new TarjetaTareaEntity();
			taskEntity.setDescripcion(((TarjetaTarea) tarjeta).getDescripcion());
			taskEntity.setFechaLimite(((TarjetaTarea) tarjeta).getFechaLimite());
			
			tarjetaEntity = taskEntity;
		}
		
		
		tarjetaEntity.setId(tarjeta.getId());
		tarjetaEntity.setNombre(tarjeta.getNombre());
		tarjetaEntity.setCompletada(tarjeta.isCompletada());
		if(tarjeta.getEtiqueta().isPresent()) {
			Etiqueta etiq = tarjeta.getEtiqueta().get();
			tarjetaEntity.setEtiquetaNombre(etiq.nombre());
			tarjetaEntity.setEtiquetaColor(etiq.color());
		}
		else {
			tarjetaEntity.setEtiquetaNombre(null);
			tarjetaEntity.setEtiquetaColor(null);
		}

		return tarjetaEntity;
	}
	
	
	
	public Tarjeta toDomain(TarjetaEntity tarjetaEntity) {
		Tarjeta tarjeta;
		Etiqueta etiq = null;
		
		if(tarjetaEntity.getEtiquetaNombre() != null && tarjetaEntity.getEtiquetaColor() != null) {
			etiq = new Etiqueta(tarjetaEntity.getEtiquetaNombre(), tarjetaEntity.getEtiquetaColor());
		}
		
		if(tarjetaEntity instanceof TarjetaCheckListEntity) {
			TarjetaCheckList tarjetaCheckList = new TarjetaCheckList(tarjetaEntity.getId(), tarjetaEntity.getNombre(), etiq);
			tarjetaCheckList.setItems(((TarjetaCheckListEntity) tarjetaEntity).getItems().stream()
																							.map(itemMapper::toDomain)
																							.toList());
			tarjeta = tarjetaCheckList;
		}
		else{
			tarjeta = new TarjetaTarea(tarjetaEntity.getId(), tarjetaEntity.getNombre(), etiq, ((TarjetaTareaEntity) tarjetaEntity).getDescripcion(), ((TarjetaTareaEntity) tarjetaEntity).getFechaLimite());
		}
		
		tarjeta.setCompletada(tarjetaEntity.isCompletada());
		
		return tarjeta;
	}
}
