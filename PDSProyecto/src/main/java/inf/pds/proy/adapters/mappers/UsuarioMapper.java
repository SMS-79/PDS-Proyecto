package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.UsuarioEntity;
import inf.pds.proy.domain.model.Usuario;

@Component
public class UsuarioMapper {

	public UsuarioEntity toEntity(Usuario user) {
		UsuarioEntity userEntity = new UsuarioEntity();
		userEntity.setId(user.getId());
		userEntity.setNombre(user.getNombre());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPswd());
		
		return userEntity;
		
	}
	
	public Usuario toDomain(UsuarioEntity userEntity) {
		return new Usuario(userEntity.getId(), userEntity.getNombre(), userEntity.getEmail(), userEntity.getPassword());		
	}
}
