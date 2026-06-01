package inf.pds.proy.adapters.rest;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.UsuarioId;
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
	public ResponseEntity<Usuario> obtener(@PathVariable UsuarioId id){
		return usuarioService.filtrarUsuarioById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obtenerUsuarios(){
		return ResponseEntity.ok(usuarioService.obtenerUsuarios());
	}
	
	
	
}
