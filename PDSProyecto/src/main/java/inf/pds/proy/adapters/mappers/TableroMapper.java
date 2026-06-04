package inf.pds.proy.adapters.mappers;

import inf.pds.proy.adapters.jpa.entity.TableroEntity;
import inf.pds.proy.domain.model.HistorialOps;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.adapters.mappers.UsuarioMapper;

public class TableroMapper {
	
	private UsuarioMapper userMapper; 
	
	public TableroEntity toEntity(Tablero table) {
		TableroEntity tableroEntity = new TableroEntity();
		tableroEntity.setBloqueado(table.isBloqueado());
		tableroEntity.setCompletedList(table.getCompletedList());	
		tableroEntity.setHistorialOp(table.getHistorialOp());
		tableroEntity.setId(table.getId());
		tableroEntity.setListaTareas(table.getListas());
		tableroEntity.setMiembros(table.getMiembros().stream()
				.map(userMapper::toEntity)
				.toList());
		tableroEntity.setNombre(table.getNombre());
		tableroEntity.setPropietario(userMapper.toEntity(table.getPropietario()));
		tableroEntity.setUrl(table.getUrl());
		return tableroEntity;
		
	}
	
	public Tablero toDomain(TableroEntity tableroEntity) {
		Tablero table = new Tablero(tableroEntity.getId(), tableroEntity.getNombre(), userMapper.toDomain(tableroEntity.getPropietario()), tableroEntity.getUrl());
		
		table.setBloqueado(tableroEntity.isBloqueado());
		table.setCompletedList(tableroEntity.getCompletedList());
		
		for(ListaTareas t : tableroEntity.getListaTareas()) {
			table.addLista(t);
		}
		
		for(Usuario u : tableroEntity.getMiembros().stream()
				.map(userMapper::toDomain)
				.toList()){
			table.addMiembro(u);
		}
		
		for(HistorialOps o : tableroEntity.getHistorialOp()) {
			table.registrarOp(o);
		}
		
		return table;
		
	}

}
