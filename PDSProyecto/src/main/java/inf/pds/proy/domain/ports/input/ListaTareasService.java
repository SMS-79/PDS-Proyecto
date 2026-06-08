package inf.pds.proy.domain.ports.input;

import java.util.List;
import java.util.Optional;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tarjeta;

public interface ListaTareasService {

    ListaTareas crearListaTareas(String tipo, Long tableroId);
    List<ListaTareas> obtenerListasTareas();
    Optional<ListaTareas> filtrarListaById(Long id);
    void eliminarLista(Long id);
    ListaTareas addTarjeta(Long listaId, Tarjeta tarjeta);
    ListaTareas elimTarjeta(Long listaId, Long tarjetaId);
}