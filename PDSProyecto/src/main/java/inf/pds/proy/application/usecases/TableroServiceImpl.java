package inf.pds.proy.application.usecases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import inf.pds.proy.domain.model.Etiqueta;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.TarjetaCheckList;
import inf.pds.proy.domain.model.TarjetaTarea;
import inf.pds.proy.domain.model.HistorialOps.TipoOperacion;
import inf.pds.proy.domain.model.Usuario;
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
	public Tablero crearTablero(String nombre, Usuario propietario, String url) throws IdentificadorTableroException {
		Tablero table = new Tablero(TableroId.random(), nombre, propietario, url);
		repTab.guardarTablero(table);
		return table;
	}

	@Override
	public List<Tablero> obtenerTableros() {
		return repTab.obtenerTableros();
	}

	@Override
	public Optional<Tablero> filtrarTableroById(TableroId id) {
		return repTab.filtrarTableroById(id);
	}

	@Override
	public Optional<Tablero> filtrarTableroByURL(String url) {
		return repTab.filtrarTableroByURL(url);
	}
	
	@Override
	public void eliminarTablero(Tablero tablero) {
		repTab.eliminarTablero(tablero);
	}

	@Override
	public void eliminarTablero(TableroId id) {
		repTab.eliminarTablero(id);
	}
	

	@Override
	@Transactional
	public ListaTareas crearLista(Tablero tablero, String tipo) {
		
		ListaTareas lista = tablero.crearLista(tipo);
		String desc = "Lista" + lista.getTipo() + " con id " + lista.getId() + " creada";
		tablero.registrarOp(TipoOperacion.LISTA_CREADA, desc, tablero.getPropietario());
		return lista;
	}

	@Override
	@Transactional
	public List<ListaTareas> obtenerListas(Tablero tablero) {
		String desc = "Listas del tablero " + tablero.getNombre() + " obtenidas";
		tablero.registrarOp(TipoOperacion.LISTAS_OBTENIDAS, desc, tablero.getPropietario());
		return tablero.getListas();
	}

	@Override
	@Transactional
	public Optional<ListaTareas> filtrarListaById(Tablero tablero, ListaTareasId id) {
		Optional<ListaTareas>listaOptional = tablero.obtenerLista(id);
		String desc = "Lista del tablero " + tablero.getNombre() + "con id " + id + " no encontrada";
		if(listaOptional.isPresent()) {
			desc = "Lista del tablero " + tablero.getNombre() + "con id " + id + " filtrada";
			tablero.registrarOp(TipoOperacion.LISTA_BUSCADA, desc, tablero.getPropietario());
		}
		
		tablero.registrarOp(TipoOperacion.LISTA_BUSCADA, desc, tablero.getPropietario());
		return listaOptional;
	}

	@Override
	@Transactional
	public void eliminarLista(Tablero tablero, ListaTareas lista) {
		String desc = "Lista" + lista.getTipo() + " con id " + lista.getId() + " eliminada";
		tablero.eliminarLista(lista);
		tablero.registrarOp(TipoOperacion.LISTA_ELIMINADA, desc, tablero.getPropietario());
	}
	
	@Override
	@Transactional
	public TarjetaTarea crearTarjetaTarea(Tablero tablero, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) {
		TarjetaTarea tarjeta = tablero.crearTarjetaTarea(listaId, nombre, etiqueta, fechaLimite, responsable);
		String desc = "Tarjeta " + tarjeta.getNombre() + ", de tipo Tarjeta_Tarea, con id " + tarjeta.getId() + " creada";
		tablero.registrarOp(TipoOperacion.TARJETA_CREADA, desc, tablero.getPropietario());
		return tarjeta;
	}
	
	@Override
	@Transactional
	public TarjetaCheckList crearTarjetaCheckList(Tablero tablero, ListaTareasId listaId, String nombre, Etiqueta etiqueta) {
		TarjetaCheckList tarjeta = tablero.crearTarjetaCheckList(listaId, nombre, etiqueta);
		String desc = "Tarjeta " + tarjeta.getNombre() + ", de tipo Tarjeta_CheckList, con id " + tarjeta.getId() + " creada";
		tablero.registrarOp(TipoOperacion.TARJETA_CREADA, desc, tablero.getPropietario());
		return tarjeta;
	}
	
	@Override
	@Transactional
	public List<Tarjeta> obtenerTarjetas(Tablero tablero, ListaTareasId listaId){
		String desc = "Tarjetas de la lista con id " + listaId + " obtenidas";
		tablero.registrarOp(TipoOperacion.TARJETAS_OBTENIDAS, desc, tablero.getPropietario());
		return tablero.getTarjetasDeLista(listaId);
	}

	@Override
	@Transactional
	public Optional<Tarjeta> filtrarTarjetasById(Tablero tablero, ListaTareasId listaId, TarjetaId tarjetaId) {
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
	public void eliminarTarjeta(Tablero tablero, ListaTareasId listaId, Tarjeta tarjeta) {
		String desc = "Tarjeta " + tarjeta.getNombre() + " con id " + tarjeta.getId() + " eliminada";
		tablero.eliminarTarjetaDeLista(listaId, tarjeta);
		tablero.registrarOp(TipoOperacion.TARJETA_ELIMINADA, desc, tablero.getPropietario());
	}

	@Override
	@Transactional
	public void moverTarjeta(Tablero tablero, ListaTareasId listaId, TarjetaId tarjetaId, ListaTareasId listaObjetivoId) {
		Optional<Tarjeta> tarjeta = tablero.obtenerTarjetaDeLista(listaId, tarjetaId);
		if(tarjeta.isPresent()) {
			tablero.eliminarTarjetaDeLista(listaId, tarjeta.get());
			tablero.addTarjetaToList(listaObjetivoId, tarjeta.get());
			String desc = "Tarjeta " + tarjeta.get().getNombre() + " con id " + tarjeta.get().getId() + " desplazada de lista " + listaId + " a lista " + listaObjetivoId;
			tablero.registrarOp(TipoOperacion.TARJETA_DESPLAZADA, desc, tablero.getPropietario());
		}
		
	}

	@Override
	public void bloquearTablero(TableroId tableroId, LocalDateTime fechaBloqueo) {
		Optional<Tablero> tablero = filtrarTableroById(tableroId);
		if(tablero.isPresent()) {
			tablero.get().bloquear(fechaBloqueo);
			String desc = "Tablero " + tablero.get().getNombre() + " bloqueado hasta " + fechaBloqueo.toString();
			tablero.get().registrarOp(TipoOperacion.TABLERO_BLOQUEADO, desc , tablero.get().getPropietario());
		}
		
	}

	@Override
	public void desbloquearTablero(TableroId tableroId) {
		Optional<Tablero> tablero = filtrarTableroById(tableroId);
		if(tablero.isPresent()) {
			tablero.get().desbloquear();
			String desc = "Tablero " + tablero.get().getNombre() + " desbloqueado.";
			tablero.get().registrarOp(TipoOperacion.TABLERO_DESBLOQUEADO, desc , tablero.get().getPropietario());
		}
		
	}
	
	
	
}
