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
import inf.pds.proy.domain.model.exceptions.TarjetaNoInsertadaException;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.model.ids.TableroId.IdentificadorTableroException;
import inf.pds.proy.domain.model.ids.TarjetaId;

public interface TableroService {
	
	Tablero crearTablero(String nombre, Usuario propietario) throws IdentificadorTableroException;
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(TableroId tableroId);
	Optional<Tablero> filtrarTableroByIdOrUrl(String id);
	void eliminarTablero(TableroId tableroId);
	void bloquearTablero(TableroId tableroId, LocalDateTime fechaBloqueo) throws TableroNoExistenteException;
	void desbloquearTablero(TableroId tableroId) throws TableroNoExistenteException;
	
	ListaTareas crearLista(TableroId tableroId, String tipo) throws TableroNoExistenteException;
	List<ListaTareas> obtenerListas(String id) throws TableroNoExistenteException;
	Optional<ListaTareas> filtrarListaById(String id, ListaTareasId listaId) throws TableroNoExistenteException;
	void setLimiteLista(TableroId tableroId, ListaTareasId listaId, int limite) throws TableroNoExistenteException, ListaNoExistenteException;
	void addCaminoLista(TableroId tableroId, ListaTareasId listaId, ListaTareasId listaCaminoId) throws TableroNoExistenteException, ListaNoExistenteException;
	void eliminarLista(TableroId tablero, ListaTareasId listaId) throws TableroNoExistenteException;
	
	TarjetaTarea crearTarjetaTarea(TableroId tableroId, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable, String descripcion) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoInsertadaException;
	TarjetaCheckList crearTarjetaCheckList(TableroId tableroId, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoInsertadaException; 
	List<Tarjeta> obtenerTarjetas(String id, ListaTareasId listaId) throws TableroNoExistenteException, ListaNoExistenteException;
	List<Tarjeta> obtenerTarjetasEtiqueta(String id, String etiqueta) throws TableroNoExistenteException;
	Optional<Tarjeta> filtrarTarjetasById(String id, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException; 
	void moverTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId, ListaTareasId listaObjetivoId) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoExistenteException, TarjetaNoInsertadaException;
	void alternarCompletarTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoExistenteException;
	void eliminarTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException,  TarjetaNoExistenteException; 
	
	
}
