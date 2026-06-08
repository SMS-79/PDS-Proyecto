package inf.pds.proy.domain.ports.input;

import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.Usuario;

public interface UsuarioService {

	Usuario crearUsuario(String nombre, String correo, String pswd);
	List<Usuario> obtenerUsuarios();
	Optional<Usuario> filtrarUsuarioById(Long id);
	Optional<Usuario> filtrarUsuarioByEmail(String email);
	void eliminarUsuario(Usuario usuario);
	void eliminarUsuario(Long id);
	
	
}
