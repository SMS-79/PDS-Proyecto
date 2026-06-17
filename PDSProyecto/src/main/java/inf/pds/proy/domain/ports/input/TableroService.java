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
import inf.pds.proy.domain.model.exceptions.ListaNoExistenteException;
import inf.pds.proy.domain.model.exceptions.TableroNoExistenteException;
import inf.pds.proy.domain.model.exceptions.TarjetaNoExistenteException;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.model.ids.TableroId.IdentificadorTableroException;
import inf.pds.proy.domain.model.ids.TarjetaId;

public interface TableroService {
	
	Tablero crearTablero(String nombre, Usuario propietario, String url) throws IdentificadorTableroException;
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(TableroId id);
	Optional<Tablero> filtrarTableroByURL(String url);
	void eliminarTablero(TableroId id);
	void bloquearTablero(TableroId tableroId, LocalDateTime fechaBloqueo) throws TableroNoExistenteException;
	void desbloquearTablero(TableroId tableroId) throws TableroNoExistenteException;
	
	ListaTareas crearLista(TableroId tableroId, String tipo) throws TableroNoExistenteException;
	List<ListaTareas> obtenerListas(TableroId tablero) throws TableroNoExistenteException;
	Optional<ListaTareas> filtrarListaById(TableroId tablero, ListaTareasId id) throws TableroNoExistenteException;
	void eliminarLista(TableroId tablero, ListaTareasId listaId) throws TableroNoExistenteException;
	
	TarjetaTarea crearTarjetaTarea(TableroId tableroId, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) throws TableroNoExistenteException, ListaNoExistenteException;
	TarjetaCheckList crearTarjetaCheckList(TableroId tableroId, ListaTareasId listaId, String nombre, Etiqueta etiqueta) throws TableroNoExistenteException, ListaNoExistenteException; 
	List<Tarjeta> obtenerTarjetas(TableroId tableroId, ListaTareasId listaId) throws TableroNoExistenteException, ListaNoExistenteException;
	List<Tarjeta> obtenerTarjetasEtiqueta(TableroId tableroId, String etiqueta) throws TableroNoExistenteException;
	Optional<Tarjeta> filtrarTarjetasById(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException; 
	void moverTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId, ListaTareasId listaObjetivoId) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoExistenteException;
	void alternarCompletarTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoExistenteException;
	void eliminarTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException,  TarjetaNoExistenteException; 
	
	
}
