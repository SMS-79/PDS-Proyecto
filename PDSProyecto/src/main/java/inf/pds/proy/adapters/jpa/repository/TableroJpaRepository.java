package inf.pds.proy.adapters.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import inf.pds.proy.adapters.jpa.entity.TableroEntity;

@Repository
public interface TableroJpaRepository extends JpaRepository<TableroEntity, Long> {
	Optional<TableroEntity> findByUrl(String url);
}
