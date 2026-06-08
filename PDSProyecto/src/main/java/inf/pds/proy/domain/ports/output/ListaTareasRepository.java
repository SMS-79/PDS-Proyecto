package inf.pds.proy.domain.ports.output;

import java.util.List;
import java.util.Optional;
import inf.pds.proy.domain.model.ListaTareas;

public interface ListaTareasRepository {

    ListaTareas guardarLista(ListaTareas lista);
    List<ListaTareas> obtenerListasTareas();
    Optional<ListaTareas> filtrarListaById(Long id);
    void eliminarLista(Long id);
}