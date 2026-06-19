package inf.pds.proy.adapters.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inf.pds.proy.adapters.jpa.entity.TableroEntity;
import inf.pds.proy.adapters.jpa.repository.TableroJpaRepository;
import inf.pds.proy.adapters.mappers.TableroMapper;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.ports.output.TableroRepository;


@Repository
public class TableroRepositoryImpl implements TableroRepository{

	
	private final TableroJpaRepository jpaRepository;
	private final TableroMapper tableMapper;
	
	public TableroRepositoryImpl(TableroJpaRepository jpaRep, TableroMapper tableMapper) {
		this.jpaRepository = jpaRep;
		this.tableMapper = tableMapper;
	}
	
	@Override
	public Tablero guardarTablero(Tablero tablero) {
		TableroEntity tableEntity = tableMapper.toEntity(tablero);
		return tableMapper.toDomain(jpaRepository.save(tableEntity));
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tablero> obtenerTableros() {
		return jpaRepository.findAll().stream()
				.map(tableMapper::toDomain)
				.toList();
	}

	@Override
	public Optional<Tablero> filtrarTableroById(TableroId id) {
		return jpaRepository.findById(id.getId()).map(tableMapper::toDomain);
	}

	@Override
	public Optional<Tablero> filtrarTableroByURL(String url) {
		return jpaRepository.findByUrl(url).map(tableMapper::toDomain);
	}

	@Override
	public void eliminarTablero(Tablero tablero) {
		jpaRepository.delete(tableMapper.toEntity(tablero));
	}

	@Override
	public void eliminarTablero(TableroId id) {
		jpaRepository.deleteById(id.getId());
	}

}
