package inf.pds.proy.domain.ports.output;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import inf.pds.proy.domain.model.Usuario;

public interface UsuarioRepository {

	
	void guardarUsuario(Usuario usuario);
	List<Usuario> obtenerUsuarios();
	Optional<Usuario> filtrarUsuarioById(UUID id);
	Optional<Usuario> filtrarUsuarioByEmail(String email);
	void eliminarUsuario(Usuario usuario);
	void eliminarUsuario(UUID id);
	
	
}
