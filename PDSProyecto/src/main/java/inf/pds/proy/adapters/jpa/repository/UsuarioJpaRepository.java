package inf.pds.proy.adapters.jpa.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.pds.proy.adapters.jpa.entity.UsuarioEntity;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {
	Optional<UsuarioEntity> findByEmail(String email);
}
