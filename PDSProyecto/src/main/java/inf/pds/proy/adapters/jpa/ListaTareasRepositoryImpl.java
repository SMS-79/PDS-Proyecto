package inf.pds.proy.adapters.jpa;

import java.util.List;
import java.util.Optional;

import inf.pds.proy.adapters.jpa.entity.ListaTareasEntity;
import inf.pds.proy.adapters.jpa.repository.ListaTareasJpaRepository;
import inf.pds.proy.adapters.mappers.ListaTareasMapper;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.ports.output.ListaTareasRepository;

public class ListaTareasRepositoryImpl implements ListaTareasRepository {

    private final ListaTareasJpaRepository jpaRepository;
    private final ListaTareasMapper listaTareasMapper;

    public ListaTareasRepositoryImpl(ListaTareasJpaRepository jpaRepository, ListaTareasMapper listaTareasMapper) {
        this.jpaRepository = jpaRepository;
        this.listaTareasMapper = listaTareasMapper;
    }

    @Override
    public ListaTareas guardarLista(ListaTareas lista) {
        ListaTareasEntity entity = listaTareasMapper.toEntity(lista);
        return listaTareasMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<ListaTareas> obtenerListasTareas() {
        return jpaRepository.findAll().stream()
                .map(listaTareasMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ListaTareas> filtrarListaById(Long id) {
        return jpaRepository.findById(id).map(listaTareasMapper::toDomain);
    }

    @Override
    public void eliminarLista(Long id) {
        jpaRepository.deleteById(id);
    }
}