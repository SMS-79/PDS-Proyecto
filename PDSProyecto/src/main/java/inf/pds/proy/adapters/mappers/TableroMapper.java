package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.TableroEntity;
import inf.pds.proy.domain.model.HistorialOps;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.TableroId;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.TableroId.IdentificadorTableroException;

@Component
public class TableroMapper {
	
	private UsuarioMapper userMapper; 
	private ListaTareasMapper listaMapper;
	private HistorialOpsMapper historialMapper;
	
	public TableroEntity toEntity(Tablero table) {
		TableroEntity tableroEntity = new TableroEntity();
		tableroEntity.setBloqueado(table.isBloqueado());
		tableroEntity.setCompletedList(listaMapper.toEntity(table.getCompletedList()));	
		tableroEntity.setHistorialOp(table.getHistorialOp().stream()
				.map(historialMapper::toEntity)
				.toList());
		tableroEntity.setId(table.getId().getId());
		tableroEntity.setListaTareas(table.getListas().stream()
				.map(listaMapper::toEntity)
				.toList());
		tableroEntity.setMiembros(table.getMiembros().stream()
				.map(userMapper::toEntity)
				.toList());
		tableroEntity.setNombre(table.getNombre());
		tableroEntity.setPropietario(userMapper.toEntity(table.getPropietario()));
		tableroEntity.setUrl(table.getUrl());
		return tableroEntity;
		
	}
	
	public Tablero toDomain(TableroEntity tableroEntity) {
		Tablero table = new Tablero();
		try {
			table = new Tablero(TableroId.of(tableroEntity.getId()), tableroEntity.getNombre(), userMapper.toDomain(tableroEntity.getPropietario()), tableroEntity.getUrl());
		}catch (IdentificadorTableroException e) {
			e.printStackTrace();
		}
			
		table.setBloqueado(tableroEntity.isBloqueado());
		table.setCompletedList(listaMapper.toDomain(tableroEntity.getCompletedList()));
		table.setListas(tableroEntity.getListaTareas().stream().map(listaMapper::toDomain).toList());
		
		for(Usuario u : tableroEntity.getMiembros().stream()
				.map(userMapper::toDomain)
				.toList()){
			table.addMiembro(u);
		}
		
		for(HistorialOps o : tableroEntity.getHistorialOp().stream().map(historialMapper::toDomain).toList()) {
			table.registrarOp(o);
		}
		
		return table;
		
	}

}
