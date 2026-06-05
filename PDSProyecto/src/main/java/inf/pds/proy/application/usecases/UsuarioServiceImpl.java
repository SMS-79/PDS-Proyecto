package inf.pds.proy.application.usecases;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import inf.pds.proy.domain.model.Usuario;
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

		Usuario user = new Usuario(new Random().nextLong(), nombre, correo, pswd);
		repUser.guardarUsuario(user);
		return user;
	}
	
	@Override
	public List<Usuario> obtenerUsuarios() {
		return repUser.obtenerUsuarios();
	}
	
	@Override
	public Optional<Usuario> filtrarUsuarioById(Long id) {
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
	public void eliminarUsuario(Long id) {
		repUser.eliminarUsuario(id);
	}

	
	

}
