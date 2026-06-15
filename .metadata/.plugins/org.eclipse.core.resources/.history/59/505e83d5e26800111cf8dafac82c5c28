package inf.pds.proy.adapters.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.pds.proy.adapters.jpa.entity.TableroEntity;

public interface TableroJpaRepository extends JpaRepository<TableroEntity, Long> {
	Optional<TableroEntity> findByURL(String url);
}
