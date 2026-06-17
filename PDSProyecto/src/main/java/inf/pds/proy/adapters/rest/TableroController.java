package inf.pds.proy.adapters.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.TarjetaTarea;
import inf.pds.proy.domain.model.exceptions.ListaNoExistenteException;
import inf.pds.proy.domain.model.exceptions.TableroNoExistenteException;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.model.ids.TableroId.IdentificadorTableroException;
import inf.pds.proy.domain.model.ids.TarjetaId;
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
		
		try {
			Tablero table = tableroService.crearTablero(tablero.getNombre(), tablero.getPropietario(), tablero.getUrl());
			return ResponseEntity.ok(table);
		} catch (IdentificadorTableroException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<Tablero>> obtenerTableros(){
		return ResponseEntity.ok(tableroService.obtenerTableros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tablero> obtener(@PathVariable Long id){
		try {
			return tableroService.filtrarTableroById(TableroId.of(id)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		} catch (IdentificadorTableroException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PatchMapping("/{id}/bloquear")
	public ResponseEntity<Tablero> bloquear(@PathVariable Long id, @RequestBody LocalDateTime fechaBloqueo){
		try {
			tableroService.bloquearTablero(TableroId.of(id), fechaBloqueo);
			return ResponseEntity.noContent().build();
		} catch (TableroNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch (IdentificadorTableroException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PatchMapping("/{id}/desbloquear")
	public ResponseEntity<Tablero> desbloquear(@PathVariable Long id){
		try {
			tableroService.desbloquearTablero(TableroId.of(id));
			return ResponseEntity.noContent().build();
		} catch (TableroNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch (IdentificadorTableroException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Tablero> eliminarTablero(@PathVariable Long id){
		try {
			tableroService.eliminarTablero(TableroId.of(id));
			return ResponseEntity.noContent().build();
		} catch (IdentificadorTableroException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/{id}/listas")
	public ResponseEntity<ListaTareas> crearLista(@PathVariable Long id, @RequestBody ListaTareas listaTarea){
		try{
			ListaTareas lista = tableroService.crearLista(TableroId.of(id), listaTarea.getTipo());
			return ResponseEntity.ok(lista);
		} catch (TableroNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch (IdentificadorTableroException e) {
			e.printStackTrace();
		} 
		
		return ResponseEntity.badRequest().build();
	}
	
	
	@GetMapping("/{id}/listas")
	public ResponseEntity<List<ListaTareas>> obtenerListasTablero(@PathVariable Long id){
		try {
			return ResponseEntity.ok(tableroService.obtenerListas(TableroId.of(id)));
		} catch (TableroNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch(IdentificadorTableroException e) {
			e.printStackTrace();
		} 
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}/listas/{listaId}")
	public ResponseEntity<ListaTareas> obtenerListaTablero(@PathVariable Long id, @PathVariable Long listaId){
		try {
			Optional<ListaTareas> lista = tableroService.filtrarListaById(TableroId.of(id), ListaTareasId.of(listaId));
			if(lista.isPresent()) {
				return ResponseEntity.ok(lista.get());
			}		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (TableroNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}/listas/{listaId}")
	public ResponseEntity<ListaTareas> eliminarListaTablero(@PathVariable Long id, @PathVariable Long listaId){
		try {
			tableroService.eliminarLista(TableroId.of(id), ListaTareasId.of(listaId));
			return ResponseEntity.noContent().build();
		} catch (TableroNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/{id}/listas/{listaId}/tarjeta")
	public ResponseEntity<Tarjeta> crearTarjeta(@PathVariable Long id, @PathVariable Long listaId, @RequestBody String tipo, @RequestBody Tarjeta tarjeta){
		try {
			Tarjeta card;
			if(tarjeta instanceof TarjetaTarea tarjetaTarea) {
				card = tableroService.crearTarjetaTarea(TableroId.of(id), ListaTareasId.of(listaId), tarjeta.getNombre(), tarjeta.getEtiqueta().orElse(null), tarjetaTarea.getFechaLimite(), tarjetaTarea.getResponsable(), tarjetaTarea.getDescripcion());
			}
			else {
				card = tableroService.crearTarjetaCheckList(TableroId.of(id), ListaTareasId.of(listaId), tarjeta.getNombre(), tarjeta.getEtiqueta().orElse(null), tarjeta.getFechaLimite(), tarjeta.getResponsable());
			}
				
			return ResponseEntity.ok(card);
				
		} catch (TableroNoExistenteException | ListaNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}/listas/{listaId}/tarjetas")
	public ResponseEntity<List<Tarjeta>> obtenerTarjetasLista(@PathVariable Long id, @PathVariable Long listaId){
		try {
			return ResponseEntity.ok(tableroService.obtenerTarjetas(TableroId.of(id), ListaTareasId.of(listaId)));
		} catch (TableroNoExistenteException | ListaNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}/listas/{listaId}/tarjetas/{tarjetaId}")
	public ResponseEntity<Tarjeta> obtenerTarjetaLista(@PathVariable Long id, @PathVariable Long listaId, @PathVariable Long tarjetaId){
		try {
			Optional<Tarjeta> tarjeta = tableroService.filtrarTarjetasById(TableroId.of(id), ListaTareasId.of(listaId), TarjetaId.of(tarjetaId));
			if(tarjeta.isPresent()) {
				return ResponseEntity.ok(tarjeta.get());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}/tarjetas/{etiqueta}")
	public ResponseEntity<List<Tarjeta>> obtenerTarjetaListaPorEtiqueta(@PathVariable Long id, @PathVariable String etiqueta){
		try {
			return ResponseEntity.ok(tableroService.obtenerTarjetasEtiqueta(TableroId.of(id), etiqueta));
		} catch (TableroNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PatchMapping("/{id}/listas/{listaId}/tarjeta/{tarjetaId}/cambiar")
	public ResponseEntity<Tarjeta> cambiarTarjeta(@PathVariable Long id, @PathVariable Long listaId, @PathVariable TarjetaId tarjetaId, @RequestBody Long listaObjId){
		try {
			tableroService.moverTarjeta(TableroId.of(id), ListaTareasId.of(listaId), tarjetaId, ListaTareasId.of(listaObjId));
			return ResponseEntity.noContent().build();
		} catch (TableroNoExistenteException | ListaNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PatchMapping("/{id}/listas/{listaId}/tarjeta/{tarjetaId}/completar")
	public ResponseEntity<Tarjeta> alternarCompletarTarjeta(@PathVariable Long id, @PathVariable Long listaId, @PathVariable TarjetaId tarjetaId, @RequestBody Long listaObjId){
		try {
			
			tableroService.alternarCompletarTarjeta(TableroId.of(id), ListaTareasId.of(listaId), tarjetaId);
			return ResponseEntity.noContent().build();
					
		} catch (TableroNoExistenteException | ListaNoExistenteException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	
	@DeleteMapping("/{id}/listas/{listaId}/tarjetas/{tarjetaId}")
	public ResponseEntity<Tarjeta> eliminarTarjetaLista(@PathVariable Long id, @PathVariable Long listaId, @PathVariable Long tarjetaId){
		try {
			tableroService.eliminarTarjeta(TableroId.of(id), ListaTareasId.of(listaId) , TarjetaId.of(tarjetaId));
			return ResponseEntity.noContent().build();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
}
