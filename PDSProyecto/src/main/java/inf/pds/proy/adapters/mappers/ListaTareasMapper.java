package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.ListaTareasEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaEntity;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.ids.ListaTareasId.IdentificadorListaException;

@Component
public class ListaTareasMapper {

    private final TarjetaMapper tarjetaMapper;

    public ListaTareasMapper(TarjetaMapper tarjetaMapper) {
        this.tarjetaMapper = tarjetaMapper;
    }

    public ListaTareasEntity toEntity(ListaTareas lista) {
        ListaTareasEntity entity = new ListaTareasEntity();
        entity.setTipo(lista.getTipo());
        entity.setTarjetas(
            lista.getTarjetas().stream()
                .map(tarjetaMapper::toEntity)
                .toList()
        );
        return entity;
    }

    public ListaTareas toDomain(ListaTareasEntity entity) {
    	try {
    		ListaTareas lista = new ListaTareas(entity.getTipo());
            entity.getTarjetas().stream()
                .map(tarjetaMapper::toDomain)
                .forEach(lista::addTarjeta);
            return lista;
    	} catch(IdentificadorListaException e) {
    		e.printStackTrace();
    	}
        return null;
    }

    // Para convertir tarjetas individuales (usado en completedList)
    public TarjetaEntity tarjetaToEntity(Tarjeta tarjeta) {
        return tarjetaMapper.toEntity(tarjeta);
    }

    public Tarjeta tarjetaToDomain(TarjetaEntity entity) {
        return tarjetaMapper.toDomain(entity);
    }
}