package inf.pds.proy.domain.ports.output;

import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.Usuario;

public interface UsuarioRepository {
	
	Usuario guardarUsuario(Usuario usuario);
	List<Usuario> obtenerUsuarios();
	Optional<Usuario> filtrarUsuarioById(Long id);
	Optional<Usuario> filtrarUsuarioByEmail(String email);
	void eliminarUsuario(Usuario usuario);
	void eliminarUsuario(Long id);
	
	
}
