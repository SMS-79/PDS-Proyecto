package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.HistorialOpsEntity;
import inf.pds.proy.domain.model.HistorialOps;



@Component
public class HistorialOpsMapper {

	private UsuarioMapper userMapper;
	
	public HistorialOpsEntity toEntity(HistorialOps historial) { 
		HistorialOpsEntity historialEntity = new HistorialOpsEntity();
		historialEntity.setId(historial.getId());
		historialEntity.setUsuario(userMapper.toEntity(historial.getUsuario()));
		historialEntity.setDescripcion(historial.getDescripcion());
		historialEntity.setTipo(historial.getTipo());
		historialEntity.setFecha(historial.getFecha());
		
		return historialEntity;
		
	}
	
	public HistorialOps toDomain(HistorialOpsEntity historialEntity) {
		return new HistorialOps(historialEntity.getId(), historialEntity.getTipo(), userMapper.toDomain(historialEntity.getUsuario()), historialEntity.getFecha());
		
	}
}
