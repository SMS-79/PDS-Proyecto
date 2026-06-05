package inf.pds.proy.adapters.mappers;

import inf.pds.proy.adapters.jpa.entity.TarjetaCheckListEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaTareaEntity;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.TarjetaCheckList;
import inf.pds.proy.domain.model.TarjetaTarea;

public class TarjetaMapper {

	public TarjetaTareaEntity toEntity(TarjetaTarea tarjeta) {
		TarjetaTareaEntity tarjetaEntity = new TarjetaTareaEntity();
		tarjetaEntity.setId(tarjeta.getId());
		tarjetaEntity.setNombre(tarjeta.getNombre());
		tarjetaEntity.setCompletada(tarjeta.isCompletada());
		tarjetaEntity.setDescripcion(tarjeta.getDescripcion());
		tarjetaEntity.setFechaLimite(tarjeta.getFechaLimite());
		tarjetaEntity.getEtiqueta().ifPresent(e -> {
			tarjetaEntity.setEtiquetaNombre(e.nombre());
			tarjetaEntity.setEtiquetaColor(e.color())});
            
		
	}
	
	public TarjetaCheckListEntity toEntity(TarjetaCheckList tarjeta) {
		
		
	}
	
	
	
	public Tarjeta toDomain(TarjetaTareaEntity tarjetaTareaEntity) {
		
		
	}
}
