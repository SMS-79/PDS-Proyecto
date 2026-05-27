package inf.pds.proy.domain.ports.input;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import inf.pds.proy.domain.model.Usuario;

public interface UsuarioService {

	Usuario crearUsuario(String nombre, String correo, String pswd);
	List<Usuario> obtenerUsuarios();
	Optional<Usuario> filtrarUsuarioById(UUID id);
	Optional<Usuario> filtrarUsuarioByEmail(String email);
	void eliminarUsuario(UUID id);
	
	
}
