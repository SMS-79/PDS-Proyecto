package inf.pds.proy.adapters.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.ports.input.TableroService;

@RestController
@RequestMapping("/api/tableros")
public class TableroController {

	private final TableroService tableroService;
	
	public TableroController(TableroService tableroService) {
		this.tableroService = tableroService;
	}
	
	@PostMapping
	public ResponseEntity<Tablero> crear(@RequestBody Tablero tablero){
		Tablero table = tableroService.crearTablero(tablero.getNombre(), tablero.getPropietario(), tablero.getUrl());
		
		return ResponseEntity.ok(table);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tablero> obtener(@PathVariable Long id){
		return tableroService.filtrarTableroById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<Tablero>> obtenerTableros(){
		return ResponseEntity.ok(tableroService.obtenerTableros());
	}
	
}
