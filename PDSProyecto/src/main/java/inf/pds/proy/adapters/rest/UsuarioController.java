package inf.pds.proy.adapters.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.UsuarioId;
import inf.pds.proy.domain.model.UsuarioId.IdentificadorUsuarioException;
import inf.pds.proy.domain.ports.input.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
		Usuario user = usuarioService.crearUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getPswd());
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtener(@PathVariable Long id){
		try {
			return usuarioService.filtrarUsuarioById(UsuarioId.of(id)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		}catch (IdentificadorUsuarioException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obtenerUsuarios(){
		return ResponseEntity.ok(usuarioService.obtenerUsuarios());
	}
	
	
	
}
