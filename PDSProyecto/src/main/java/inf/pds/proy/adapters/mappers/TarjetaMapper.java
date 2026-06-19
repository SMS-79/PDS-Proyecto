package inf.pds.proy.adapters.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.TarjetaCheckListEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaTareaEntity;
import inf.pds.proy.domain.model.Etiqueta;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.TarjetaCheckList;
import inf.pds.proy.domain.model.TarjetaTarea;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.ListaTareasId.IdentificadorListaException;
import inf.pds.proy.domain.model.ids.TarjetaId;

@Component
public class TarjetaMapper {
	
	CheckListItemMapper itemMapper;
	UsuarioMapper userMapper;
	ListaTareasMapper listaMapper;
	
	@Autowired
	public TarjetaMapper(CheckListItemMapper itemMapper, UsuarioMapper userMapper, @Lazy ListaTareasMapper listaMapper) {
		this.itemMapper = itemMapper;
		this.userMapper = userMapper;
		this.listaMapper = listaMapper;
	}

	public TarjetaEntity toEntity(Tarjeta tarjeta) {
		TarjetaEntity tarjetaEntity;
		
		if(tarjeta instanceof TarjetaTarea tarjetaTarea) {
			
			TarjetaTareaEntity taskEntity = new TarjetaTareaEntity();
			taskEntity.setDescripcion(tarjetaTarea.getDescripcion());
			
			tarjetaEntity = taskEntity;
		}
		else{
			TarjetaCheckListEntity checkListEntity = new TarjetaCheckListEntity();
			checkListEntity.setItems(((TarjetaCheckList) tarjeta).getItems().stream()
																			.map(itemMapper::toEntity)
																			.toList());
			tarjetaEntity = checkListEntity;
		}
		
		
		tarjetaEntity.setId(tarjeta.getId().getId());
		tarjetaEntity.setNombre(tarjeta.getNombre());
		tarjetaEntity.setCompletada(tarjeta.isCompletada());
		
		Etiqueta etiq = tarjeta.getEtiqueta().orElseGet(() -> new Etiqueta(null, null));
		
		tarjetaEntity.setEtiquetaNombre(etiq.nombre());
		tarjetaEntity.setEtiquetaColor(etiq.color());
		tarjetaEntity.setFechaLimite(tarjeta.getFechaLimite());
		tarjetaEntity.setResponsable(userMapper.toEntity(tarjeta.getResponsable()));
		tarjetaEntity.setHistorialLista(tarjeta.getHistorialLista().stream().map(l -> l.getId()).toList());
		

		return tarjetaEntity;
	}
	
	
	
	public Tarjeta toDomain(TarjetaEntity tarjetaEntity) {
		Tarjeta tarjeta = null;
		Etiqueta etiq = null;
		if(tarjetaEntity.getEtiquetaNombre() != null && tarjetaEntity.getEtiquetaColor() != null) {
			etiq = new Etiqueta(tarjetaEntity.getEtiquetaNombre(), tarjetaEntity.getEtiquetaColor());
		}
		
		try {
			if(tarjetaEntity instanceof TarjetaTareaEntity taskEntity) {
				TarjetaTarea tarjetaTarea = new TarjetaTarea(TarjetaId.of(tarjetaEntity.getId()), tarjetaEntity.getNombre(), etiq, tarjetaEntity.getFechaLimite(), userMapper.toDomain(tarjetaEntity.getResponsable()), taskEntity.getDescripcion());
				tarjeta = tarjetaTarea;
			}
			else{
				TarjetaCheckList tarjetaCheckList = new TarjetaCheckList(TarjetaId.of(tarjetaEntity.getId()), tarjetaEntity.getNombre(), etiq, tarjetaEntity.getFechaLimite(), userMapper.toDomain(tarjetaEntity.getResponsable()));
				tarjetaCheckList.setItems(((TarjetaCheckListEntity) tarjetaEntity).getItems().stream()
																								.map(itemMapper::toDomain)
																								.toList());
				tarjeta = tarjetaCheckList;
			}
			tarjeta.setCompletada(tarjetaEntity.isCompletada());
			tarjeta.setHistorialLista(tarjetaEntity.getHistorialLista().stream().map(l -> {
				try {
					return ListaTareasId.of(l);
				} catch (IdentificadorListaException e) {
					e.printStackTrace();
					return ListaTareasId.random();
				}
			}).toList());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return tarjeta;
	}
}
