package inf.pds.proy.adapters.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import inf.pds.proy.adapters.jpa.entity.UsuarioEntity;
import inf.pds.proy.adapters.jpa.repository.UsuarioJpaRepository;
import inf.pds.proy.adapters.mappers.UsuarioMapper;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.ports.output.UsuarioRepository;

public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	private final UsuarioJpaRepository jpaRepository;
	private final UsuarioMapper userMapper;
	
	public UsuarioRepositoryImpl(UsuarioJpaRepository jpaRep, UsuarioMapper userMapper) {
		this.jpaRepository = jpaRep;
		this.userMapper = userMapper;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		UsuarioEntity userEntity = userMapper.toEntity(usuario);
		jpaRepository.save(userEntity);
		
	}
	
	@Override
	public List<Usuario> obtenerUsuarios() {
		return jpaRepository.findAll().stream()
				.map(userMapper::toDomain)
				.toList();
	}

	@Override
	public Optional<Usuario> filtrarUsuarioById(UUID id) {
		return jpaRepository.findById(id).map(userMapper::toDomain);
	}

	@Override
	public Optional<Usuario> filtrarUsuarioByEmail(String email) {
		return jpaRepository.findByEmail(email).map(userMapper::toDomain);
	}
	
	@Override
	public void eliminarUsuario(Usuario usuario) {
		jpaRepository.delete(userMapper.toEntity(usuario));
	}

	@Override
	public void eliminarUsuario(UUID id) {
		jpaRepository.deleteById(id);
		
	}

	
}
