package inf.pds.proy.application.usecases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import inf.pds.proy.domain.model.Etiqueta;
import inf.pds.proy.domain.model.HistorialOps.TipoOperacion;
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
import inf.pds.proy.domain.ports.input.TableroService;
import inf.pds.proy.domain.ports.output.TableroRepository;
import jakarta.transaction.Transactional;

@Service
public class TableroServiceImpl implements TableroService{

	private TableroRepository repTab;
	
	public TableroServiceImpl(TableroRepository rep) {
		this.repTab = rep;
	}

	@Override
	public Tablero crearTablero(String nombre, Usuario propietario) throws IdentificadorTableroException {
		Tablero table = new Tablero(TableroId.random(), nombre, propietario);
		System.out.println(table.getId());
		return repTab.guardarTablero(table);
	}

	@Override
	public List<Tablero> obtenerTableros() {
		return repTab.obtenerTableros();
	}
	
	@Override
	public Optional<Tablero> filtrarTableroById(TableroId tableroId) {
		return repTab.filtrarTableroById(tableroId);
	}

	@Override
	public Optional<Tablero> filtrarTableroByIdOrUrl(String id) {
		try {
			Long idNum = Long.parseLong(id);
			return repTab.filtrarTableroById(TableroId.of(idNum));
		}catch(NumberFormatException | IdentificadorTableroException e) {
			return repTab.filtrarTableroByURL(id);
		}
		
	}


	@Override
	public void eliminarTablero(TableroId id) {
		repTab.eliminarTablero(id);
	}
	
	@Override
	public void bloquearTablero(TableroId tableroId, LocalDateTime fechaBloqueo) throws TableroNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		tablero.bloquear(fechaBloqueo);
		String desc = "Tablero " + tablero.getNombre() + " bloqueado hasta " + fechaBloqueo.toString();
		tablero.registrarOp(TipoOperacion.TABLERO_BLOQUEADO, desc , tablero.getPropietario());
	}

	@Override
	public void desbloquearTablero(TableroId tableroId) throws TableroNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		tablero.desbloquear();
		String desc = "Tablero " + tablero.getNombre() + " desbloqueado.";
		tablero.registrarOp(TipoOperacion.TABLERO_DESBLOQUEADO, desc , tablero.getPropietario());
	}
	

	@Override
	@Transactional
	public ListaTareas crearLista(TableroId tableroId, String tipo) throws TableroNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		ListaTareas lista = tablero.crearLista(tipo);
		String desc = "Lista" + lista.getTipo() + " con id " + lista.getId() + " creada";
		tablero.registrarOp(TipoOperacion.LISTA_CREADA, desc, tablero.getPropietario());
		return lista;
	}

	@Override
	@Transactional
	public List<ListaTareas> obtenerListas(String id) throws TableroNoExistenteException{
		Tablero tablero = filtrarTableroByIdOrUrl(id).orElseThrow(() -> new TableroNoExistenteException("Tablero con id o url " + id + " no encontrado"));
		String desc = "Listas del tablero " + tablero.getNombre() + " obtenidas";
		tablero.registrarOp(TipoOperacion.LISTAS_OBTENIDAS, desc, tablero.getPropietario());
		return tablero.getListas();	
	}

	@Override
	@Transactional
	public Optional<ListaTareas> filtrarListaById(String id, ListaTareasId listaId) throws TableroNoExistenteException{
		Tablero tablero = filtrarTableroByIdOrUrl(id).orElseThrow(() -> new TableroNoExistenteException("Tablero con id o url " + id + " no encontrado"));
		Optional<ListaTareas> listaOptional = tablero.obtenerLista(listaId);
		String desc;
		if(listaOptional.isPresent()) {
			desc = "Lista del tablero " + tablero.getNombre() + "con id " + listaId + " filtrada";
		}
		desc = "Lista del tablero " + tablero.getNombre() + "con id " + listaId + " no encontrada";
		tablero.registrarOp(TipoOperacion.LISTA_BUSCADA, desc, tablero.getPropietario());
		return listaOptional;
	}
	
	@Override
	@Transactional
	public void setLimiteLista(TableroId tableroId, ListaTareasId listaId, int limite) throws TableroNoExistenteException, ListaNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		ListaTareas lista = tablero.obtenerLista(listaId).orElseThrow(() -> new ListaNoExistenteException("Lista con id " + listaId + " no encontrada"));
		lista.setLimiteItems(limite);
	}

	@Override
	@Transactional
	public void addCaminoLista(TableroId tableroId, ListaTareasId listaId, ListaTareasId listaCaminoId) throws TableroNoExistenteException, ListaNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		ListaTareas lista = tablero.obtenerLista(listaId).orElseThrow(() -> new ListaNoExistenteException("Lista con id " + listaId + " no encontrada"));
		ListaTareas listaCamino = tablero.obtenerLista(listaId).orElseThrow(() -> new ListaNoExistenteException("Lista con id " + listaId + " no encontrada"));
		
		lista.addCaminoRequerido(List.of(listaCamino));
	}
	
	@Override
	@Transactional
	public void eliminarLista(TableroId tableroId, ListaTareasId listaId)  throws TableroNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		tablero.eliminarLista(listaId);
		String desc = "Lista con id " + listaId + " eliminada";
		tablero.registrarOp(TipoOperacion.LISTA_ELIMINADA, desc, tablero.getPropietario());
	}
	
	@Override
	@Transactional
	public TarjetaTarea crearTarjetaTarea(TableroId tableroId, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable, String descripcion) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoInsertadaException {
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		TarjetaTarea tarjeta = tablero.crearTarjetaTarea(listaId, nombre, etiqueta, fechaLimite, responsable, descripcion);
		String desc = "Tarjeta " + tarjeta.getNombre() + ", de tipo Tarjeta_Tarea, con id " + tarjeta.getId() + " creada";
		tablero.registrarOp(TipoOperacion.TARJETA_CREADA, desc, tablero.getPropietario());
		return tarjeta;	
	}
	
	@Override
	@Transactional
	public TarjetaCheckList crearTarjetaCheckList(TableroId tableroId, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoInsertadaException {
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		TarjetaCheckList tarjeta = tablero.crearTarjetaCheckList(listaId, nombre, etiqueta, fechaLimite, responsable);
		String desc = "Tarjeta " + tarjeta.getNombre() + ", de tipo Tarjeta_CheckList, con id " + tarjeta.getId() + " creada";
		tablero.registrarOp(TipoOperacion.TARJETA_CREADA, desc, tablero.getPropietario());
		return tarjeta;
	}
	
	@Override
	@Transactional
	public List<Tarjeta> obtenerTarjetas(String id, ListaTareasId listaId) throws TableroNoExistenteException, ListaNoExistenteException{
		Tablero tablero = filtrarTableroByIdOrUrl(id).orElseThrow(() -> new TableroNoExistenteException("Tablero con id o url " + id + " no encontrado"));
		String desc = "Tarjetas de la lista con id " + listaId + " obtenidas";
		tablero.registrarOp(TipoOperacion.TARJETAS_OBTENIDAS, desc, tablero.getPropietario());
		return tablero.getTarjetasDeLista(listaId);	
	}
	
	@Override
	@Transactional
	public List<Tarjeta> obtenerTarjetasEtiqueta(String id, String etiqueta) throws TableroNoExistenteException{
		Tablero tablero = filtrarTableroByIdOrUrl(id).orElseThrow(() -> new TableroNoExistenteException("Tablero con id o url " + id + " no encontrado"));
		String desc = "Tarjetas con etiqueta " + etiqueta + " obtenidas.";
		tablero.registrarOp(TipoOperacion.TARJETAS_OBTENIDAS, desc, tablero.getPropietario());
		return tablero.getTarjetasPorEtiqueta(etiqueta);	
	}

	@Override
	@Transactional
	public Optional<Tarjeta> filtrarTarjetasById(String id, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException{
		Tablero tablero = filtrarTableroByIdOrUrl(id).orElseThrow(() -> new TableroNoExistenteException("Tablero con id o url " + id + " no encontrado"));
		Optional<Tarjeta> tarjetaOptional = tablero.obtenerTarjetaDeLista(listaId, tarjetaId);
		String desc = "Tarjeta de la lista " + listaId + " con id " + tarjetaId + " no encontrada";
		if(tarjetaOptional.isPresent()) {
			desc = "Tarjeta " + tarjetaOptional.get().getNombre() + " de la lista " + listaId + "con id " + tarjetaId + " filtrada";
		}
		tablero.registrarOp(TipoOperacion.TARJETA_BUSCADA, desc, tablero.getPropietario());
		return tarjetaOptional;
	}
	
	@Override
	@Transactional
	public void moverTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId, ListaTareasId listaObjetivoId) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoExistenteException, TarjetaNoInsertadaException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		Tarjeta tarjeta = tablero.obtenerTarjetaDeLista(listaId, tarjetaId).orElseThrow(() -> new TarjetaNoExistenteException("Tarjeta con id " + tarjetaId + " no encontrada"));
		tablero.addTarjetaToList(listaObjetivoId, tarjeta);
		tablero.eliminarTarjetaDeLista(listaId, tarjetaId);
		String desc = "Tarjeta " + tarjeta.getNombre() + " con id " + tarjeta.getId() + " desplazada de lista " + listaId + " a lista " + listaObjetivoId;
		tablero.registrarOp(TipoOperacion.TARJETA_DESPLAZADA, desc, tablero.getPropietario());	
	}
	
	@Override
	@Transactional
	public void alternarCompletarTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException,  TarjetaNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		String desc = "Tarjeta con id " + tarjetaId + " completada";
		tablero.alternarCompletarTarjeta(listaId, tarjetaId);
		tablero.registrarOp(TipoOperacion.TARJETA_ELIMINADA, desc, tablero.getPropietario());	
	}

	@Override
	@Transactional
	public void eliminarTarjeta(TableroId tableroId, ListaTareasId listaId, TarjetaId tarjetaId) throws TableroNoExistenteException, ListaNoExistenteException, TarjetaNoExistenteException{
		Tablero tablero = filtrarTableroById(tableroId).orElseThrow(() -> new TableroNoExistenteException("Tablero con id " + tableroId + " no encontrado"));
		String desc = "Tarjeta con id " + tarjetaId + " eliminada";
		tablero.eliminarTarjetaDeLista(listaId, tarjetaId);
		tablero.registrarOp(TipoOperacion.TARJETA_ELIMINADA, desc, tablero.getPropietario());
	}

	

	

	
	
	
	
}
