package inf.pds.proy.domain.ports.input;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.Etiqueta;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.TarjetaCheckList;
import inf.pds.proy.domain.model.TarjetaTarea;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.model.ids.TableroId.IdentificadorTableroException;
import inf.pds.proy.domain.model.ids.TarjetaId;

public interface TableroService {
	
	Tablero crearTablero(String nombre, Usuario propietario, String url) throws IdentificadorTableroException;
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(TableroId id);
	Optional<Tablero> filtrarTableroByURL(String url);
	void eliminarTablero(Tablero tablero);
	void eliminarTablero(TableroId id);
	void bloquearTablero(TableroId tableroId, LocalDateTime fechaBloqueo);
	void desbloquearTablero(TableroId tableroId);
	
	ListaTareas crearLista(Tablero tablero, String tipo);
	List<ListaTareas> obtenerListas(Tablero tablero);
	Optional<ListaTareas> filtrarListaById(Tablero tablero, ListaTareasId id);
	void eliminarLista(Tablero tablero, ListaTareas lista);
	
	TarjetaTarea crearTarjetaTarea(Tablero tablero, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable);
	TarjetaCheckList crearTarjetaCheckList(Tablero tablero, ListaTareasId listaId, String nombre, Etiqueta etiqueta); 
	List<Tarjeta> obtenerTarjetas(Tablero tablero, ListaTareasId listaId);
	Optional<Tarjeta> filtrarTarjetasById(Tablero tablero, ListaTareasId listaId, TarjetaId tarjetaId); 
	void moverTarjeta(Tablero tablero, ListaTareasId listaId, TarjetaId tarjetaId, ListaTareasId listaObjetivoId);
	void alternarCompletarTarjeta(Tablero tablero, ListaTareasId listaId, TarjetaId tarjetaId);
	void eliminarTarjeta(Tablero tablero, ListaTareasId listaId, Tarjeta tarjeta); 
	
	
}
