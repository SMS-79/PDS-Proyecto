package inf.pds.proy.adapters.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.ListaTareasEntity;
import inf.pds.proy.adapters.jpa.entity.TarjetaEntity;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.ListaTareasId.IdentificadorListaException;

@Component
public class ListaTareasMapper {

    private final TarjetaMapper tarjetaMapper;

    @Autowired
    public ListaTareasMapper(@Lazy TarjetaMapper tarjetaMapper) {
        this.tarjetaMapper = tarjetaMapper;
    }

    public ListaTareasEntity toEntity(ListaTareas lista) {
        ListaTareasEntity entity = new ListaTareasEntity();
        System.out.println(lista.getId());
        entity.setId(lista.getId().getId());
        entity.setTipo(lista.getTipo());
        entity.setTarjetas(
            lista.getTarjetas().stream()
                .map(tarjetaMapper::toEntity)
                .toList()
        );
        entity.setLimiteItems(lista.getLimiteItems());
        if (lista.getCaminoRequerido() != null) {
        entity.setCaminoRequerido(lista.getCaminoRequerido().stream().map(this::toEntity).toList());
        } else {
        	entity.setCaminoRequerido(null); 
        }
        
        return entity;
        
    }

    public ListaTareas toDomain(ListaTareasEntity entity) {
    	try {
    		ListaTareas lista = new ListaTareas(ListaTareasId.of(entity.getId()), entity.getTipo());
            entity.getTarjetas().stream()
                .map(tarjetaMapper::toDomain)
                .forEach(lista::addTarjeta);
            lista.setLimiteItems(entity.getLimiteItems());
            
            if(entity.getCaminoRequerido() != null) {
                lista.setCaminoRequerido(entity.getCaminoRequerido().stream().map(this::toDomain).toList());
            }
            else {
            	lista.setCaminoRequerido(null);
            }
            
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