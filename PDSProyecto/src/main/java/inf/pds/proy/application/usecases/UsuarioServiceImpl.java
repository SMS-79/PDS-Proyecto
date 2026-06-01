package inf.pds.proy.application.usecases;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.UsuarioId;
import inf.pds.proy.domain.model.UsuarioId.IdentificadorUsuarioException;
import inf.pds.proy.domain.ports.input.UsuarioService;
import inf.pds.proy.domain.ports.output.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository repUser;
	
	public UsuarioServiceImpl(UsuarioRepository rep) {
		this.repUser = rep;
	}
	
	@Override
	public Usuario crearUsuario(String nombre, String correo, String pswd) {
		
		try {
			Usuario user = new Usuario(UsuarioId.of(new Random().nextLong()), nombre, correo, pswd);
			repUser.guardarUsuario(user);
			return user;
		} catch(IdentificadorUsuarioException e) {
			e.printStackTrace();
		}
		return null;
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
	public void eliminarUsuario(UsuarioId id) {
		repUser.eliminarUsuario(id);
	}

	
	

}
