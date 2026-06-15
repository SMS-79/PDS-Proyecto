package inf.pds.proy.adapters.jpa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.pds.proy.adapters.jpa.entity.UsuarioEntity;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
	Optional<UsuarioEntity> findByEmail(String email);
}
