package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.ListaTareasEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaTareaEntity;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tarjeta;

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
        ListaTareas lista = new ListaTareas(entity.getTipo());
        entity.getTarjetas().stream()
            .map(tarjetaMapper::toDomain)
            .forEach(lista::addTarjeta);
        return lista;
    }

    // Para convertir tarjetas individuales (usado en completedList)
    public TarjetaTareaEntity tarjetaToEntity(Tarjeta tarjeta) {
        return tarjetaMapper.toEntity(tarjeta);
    }

    public Tarjeta tarjetaToDomain(TarjetaTareaEntity entity) {
        return tarjetaMapper.toDomain(entity);
    }
}