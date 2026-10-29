package inf.pds.proy.adapters.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.ids.UsuarioId;
import inf.pds.proy.domain.model.ids.UsuarioId.IdentificadorUsuarioException;
import inf.pds.proy.domain.ports.input.UsuarioService;
import inf.pds.proy.adapters.mappers.UsuarioMapper;
import inf.pds.proy.adapters.rest.dto.UsuarioDTO;
import inf.pds.proy.adapters.rest.dto.UsuarioRespuestaDTO;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final UsuarioMapper usuarioMapper;


	public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody UsuarioDTO usuario){

		try{
			Usuario user = usuarioService.crearUsuario(
				usuario.getNombre(),
				usuario.getEmail(),
				usuario.getPswd()
			);
			UsuarioRespuestaDTO respuesta = usuarioMapper.toRespuestaDTO(user);
			return ResponseEntity.ok(respuesta);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(Map.of("error", e.getMessage()));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioRespuestaDTO> obtener(@PathVariable Long id){
		try {
			return usuarioService.filtrarUsuarioById(UsuarioId.of(id))
					.map(usuarioMapper::toRespuestaDTO)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
		}catch (IdentificadorUsuarioException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioRespuestaDTO>> obtenerUsuarios(){
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		List<UsuarioRespuestaDTO> respuesta = usuarios.stream()
														.map(usuarioMapper::toRespuestaDTO)
														.collect(Collectors.toList());

		return ResponseEntity.ok(respuesta);
	}
	
	
	
}
