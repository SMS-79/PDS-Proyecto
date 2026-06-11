package inf.pds.proy.application.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.UsuarioId;
import inf.pds.proy.domain.ports.input.UsuarioService;
import inf.pds.proy.domain.ports.output.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository repUser;
	
	public UsuarioServiceImpl(UsuarioRepository rep) {
		this.repUser = rep;
	}
	
	@Override
	public Usuario crearUsuario(String nombre, String correo, String pswd) {
		Usuario user = new Usuario(UsuarioId.random(), nombre, correo, pswd);
		repUser.guardarUsuario(user);
		return user;
	}
	
	@Override
	public List<Usuario> obtenerUsuarios() {
		return repUser.obtenerUsuarios();
	}
	
	@Override
	public Optional<Usuario> filtrarUsuarioById(UsuarioId id) {
		return repUser.filtrarUsuarioById(id); 
	}

	@Override
	public Optional<Usuario> filtrarUsuarioByEmail(String email) {
		return repUser.filtrarUsuarioByEmail(email); 
	}
	
	@Override
	public void eliminarUsuario(Usuario usuario) {
		repUser.eliminarUsuario(usuario);
	}
	
	@Override
	public void eliminarUsuario(UsuarioId id) {
		repUser.eliminarUsuario(id);
	}

	
	

}
