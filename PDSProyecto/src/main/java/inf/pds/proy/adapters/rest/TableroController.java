package inf.pds.proy.adapters.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import inf.pds.proy.domain.model.ListaTareas;
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Tablero> eliminarTablero(@PathVariable Long id){
		tableroService.eliminarTablero(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{id}/listas")
	public ResponseEntity<ListaTareas> crearLista(@PathVariable Long id, @RequestBody ListaTareas listaTarea){
		Optional<Tablero> tablero = tableroService.filtrarTableroById(id);
		if(tablero.isPresent()) {
			ListaTareas lista = tableroService.crearLista(tablero.get(), listaTarea.getTipo());
			return ResponseEntity.ok(lista);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	
	@GetMapping("/{id}/listas")
	public ResponseEntity<List<ListaTareas>> obtenerListasTablero(@PathVariable Long id){
		Optional<Tablero> tablero = tableroService.filtrarTableroById(id);
		if(tablero.isPresent()) {
			return ResponseEntity.ok(tableroService.obtenerListas(tablero.get()));
		}
		return ResponseEntity.notFound().build();

	}
	
	@GetMapping("/{id}/listas/{listaId}")
	public ResponseEntity<ListaTareas> obtenerListasTablero(@PathVariable Long id, @PathVariable Long listaId){
		Optional<Tablero> tablero = tableroService.filtrarTableroById(id);
		if(tablero.isPresent()) {
			Optional<ListaTareas> lista =  tableroService.filtrarListaById(tablero.get(), listaId);
			if(lista.isPresent()) {
				return ResponseEntity.ok(lista.get());
			}		
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}/listas/{listaId}")
	public ResponseEntity<Tablero> eliminarTablero(@PathVariable Long id, @PathVariable Long listaId){
		Optional<Tablero> tablero = tableroService.filtrarTableroById(id);
		if(tablero.isPresent()) {
			Optional<ListaTareas> lista =  tableroService.filtrarListaById(tablero.get(), listaId);
			if(lista.isPresent()) {
				tableroService.eliminarLista(tablero.get(), lista.get());
				return ResponseEntity.noContent().build();
			}		
		}
		return ResponseEntity.notFound().build();
	}
}
