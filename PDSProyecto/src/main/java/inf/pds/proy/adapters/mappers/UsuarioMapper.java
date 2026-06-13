package inf.pds.proy.adapters.mappers;

import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.entity.UsuarioEntity;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.ids.UsuarioId;
import inf.pds.proy.domain.model.ids.UsuarioId.IdentificadorUsuarioException;

@Component
public class UsuarioMapper {

	public UsuarioEntity toEntity(Usuario user) {
		UsuarioEntity userEntity = new UsuarioEntity();
		userEntity.setId(user.getId().getId());
		userEntity.setNombre(user.getNombre());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPswd());
		
		return userEntity;
		
	}
	
	public Usuario toDomain(UsuarioEntity userEntity) {
		try {
			return new Usuario(UsuarioId.of(userEntity.getId()), userEntity.getNombre(), userEntity.getEmail(), userEntity.getPassword());		
		} catch(IdentificadorUsuarioException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
