package inf.pds.proy.adapters.jpa.repository;

import java.net.URL;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.pds.proy.adapters.jpa.entity.TableroEntity;
import inf.pds.proy.domain.model.TableroId;

public interface TableroJpaRepository extends JpaRepository<TableroEntity, TableroId> {
	Optional<TableroEntity> findByURL(URL url);
}
