package inf.pds.proy.application.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.ports.input.ListaTareasService;
import inf.pds.proy.domain.ports.output.ListaTareasRepository;

@Service
public class ListaTareasServiceImpl implements ListaTareasService {

    private final ListaTareasRepository repLista;

    public ListaTareasServiceImpl(ListaTareasRepository repLista) {
        this.repLista = repLista;
    }

    @Override
    public ListaTareas crearListaTareas(String tipo, Long tableroId) {
        ListaTareas lista = new ListaTareas(tipo);
        return repLista.guardarLista(lista);
    }

    @Override
    public List<ListaTareas> obtenerListasTareas() {
        return repLista.obtenerListasTareas();
    }

    @Override
    public Optional<ListaTareas> filtrarListaById(Long id) {
        return repLista.filtrarListaById(id);
    }

    @Override
    public void eliminarLista(Long id) {
        repLista.eliminarLista(id);
    }

    @Override
    public ListaTareas addTarjeta(Long listaId, Tarjeta tarjeta) {
        ListaTareas lista = repLista.filtrarListaById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada: " + listaId));
        lista.addTarjeta(tarjeta);
        return repLista.guardarLista(lista);
    }

    @Override
    public ListaTareas elimTarjeta(Long listaId, Long tarjetaId) {
        ListaTareas lista = repLista.filtrarListaById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada: " + listaId));
        lista.getTarjetas().stream()
            .filter(t -> t.getId().equals(tarjetaId))
            .findFirst()
            .ifPresent(lista::removeTarjeta);
        return repLista.guardarLista(lista);
    }
}