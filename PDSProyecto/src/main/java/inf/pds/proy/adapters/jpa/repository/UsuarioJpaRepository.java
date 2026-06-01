package inf.pds.proy.adapters.jpa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.pds.proy.adapters.jpa.entity.UsuarioEntity;
import inf.pds.proy.domain.model.UsuarioId;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UsuarioId> {
	Optional<UsuarioEntity> findByEmail(String email);
}
