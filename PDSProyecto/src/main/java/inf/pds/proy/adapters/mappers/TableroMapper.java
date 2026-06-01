package inf.pds.proy.adapters.mappers;

import inf.pds.proy.adapters.jpa.entity.TableroEntity;
import inf.pds.proy.domain.model.Tablero;

public class TableroMapper {
	
	public TableroEntity toEntity(Tablero table) {
		TableroEntity tableroEntity = new TableroEntity();
		tableroEntity.setBloqueado(table.isBloqueado());
		tableroEntity.setCompletedList(table.getCompletedList());	
		tableroEntity.setHistorialOp(table.getHistorialOp());
		tableroEntity.setId(table.getId());
		tableroEntity.setListaTareas(table.getListas());
		tableroEntity.setMiembros(table.getMiembros());
		tableroEntity.setNombre(table.getNombre());
		tableroEntity.setPropietario(table.getPropietario());
		tableroEntity.setUrl(table.getUrl());
		return tableroEntity;
		
	}
	
	public Tablero toDomain(TableroEntity tableroEntity) {
		Tablero table = new Tablero(tableroEntity.getId(), tableroEntity.getNombre(), tableroEntity.getPropietario(), tableroEntity.getUrl());
		
		tableroEntity.setBloqueado(table.isBloqueado());
		tableroEntity.setCompletedList(table.getCompletedList());	
		tableroEntity.setHistorialOp(table.getHistorialOp());
		tableroEntity.setId(table.getId());
		tableroEntity.setListaTareas(table.getListas());
		tableroEntity.setMiembros(table.getMiembros());
		tableroEntity.setNombre(table.getNombre());
		tableroEntity.setPropietario(table.getPropietario());
		tableroEntity.setUrl(table.getUrl());
		
		return table;
		
	}

}
