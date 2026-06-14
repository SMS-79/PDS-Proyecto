package inf.pds.proy.application.usecases;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import inf.pds.proy.domain.model.Etiqueta;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Tarjeta;
import inf.pds.proy.domain.model.TarjetaCheckList;
import inf.pds.proy.domain.model.TarjetaTarea;
import inf.pds.proy.domain.model.TipoOperacion;
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
		tablero.registrarOp(TipoOperacion.LISTA_CREADA, tablero.getPropietario());
		return tablero.crearLista(tipo);
	}

	@Override
	@Transactional
	public List<ListaTareas> obtenerListas(Tablero tablero) {
		tablero.registrarOp(TipoOperacion.LISTAS_OBTENIDAS, tablero.getPropietario());
		return tablero.getListas();
	}

	@Override
	@Transactional
	public Optional<ListaTareas> filtrarListaById(Tablero tablero, ListaTareasId id) {
		tablero.registrarOp(TipoOperacion.LISTA_BUSCADA, tablero.getPropietario());
		return tablero.obtenerLista(id);
	}

	@Override
	@Transactional
	public void eliminarLista(Tablero tablero, ListaTareas lista) {
		tablero.registrarOp(TipoOperacion.LISTA_ELIMINADA, tablero.getPropietario());
		tablero.eliminarLista(lista);
	}
	
	@Override
	@Transactional
	public TarjetaTarea crearTarjetaTarea(Tablero tablero, ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) {
		tablero.registrarOp(TipoOperacion.TARJETA_CREADA, tablero.getPropietario());
		return tablero.crearTarjetaTarea(listaId, nombre, etiqueta, fechaLimite, responsable);
	}
	
	@Override
	@Transactional
	public TarjetaCheckList crearTarjetaCheckList(Tablero tablero, ListaTareasId listaId, String nombre, Etiqueta etiqueta) {
		tablero.registrarOp(TipoOperacion.TARJETA_CREADA, tablero.getPropietario());
		return tablero.crearTarjetaCheckList(listaId, nombre, etiqueta);
	}
	
	@Override
	@Transactional
	public List<Tarjeta> obtenerTarjetas(Tablero tablero, ListaTareasId listaId){
		tablero.registrarOp(TipoOperacion.TARJETAS_OBTENIDAS, tablero.getPropietario());
		return tablero.getTarjetasDeLista(listaId);
	}

	@Override
	@Transactional
	public Optional<Tarjeta> filtrarTarjetasById(Tablero tablero, ListaTareasId listaId, TarjetaId tarjetaId) {
		tablero.registrarOp(TipoOperacion.TARJETA_BUSCADA, tablero.getPropietario());
		return tablero.obtenerTarjetaDeLista(listaId, tarjetaId);
	}

	@Override
	@Transactional
	public void eliminarTarjeta(Tablero tablero, ListaTareasId listaId, Tarjeta tarjeta) {
		tablero.eliminarTarjetaDeLista(listaId, tarjeta);
		tablero.registrarOp(TipoOperacion.TARJETA_ELIMINADA, tablero.getPropietario());
	}

	@Override
	public void moverTarjeta(Tablero tablero, ListaTareasId listaId, TarjetaId tarjetaId, ListaTareasId listaObjetivoId) {
		Optional<Tarjeta> tarjeta = tablero.obtenerTarjetaDeLista(listaId, tarjetaId);
		if(tarjeta.isPresent()) {
			tablero.eliminarTarjetaDeLista(listaId, tarjeta.get());
			tablero.addTarjetaToList(listaObjetivoId, tarjeta.get());
		}
		tablero.registrarOp(TipoOperacion.TARJETA_DESPLAZADA, tablero.getPropietario());
	}
	
	
	
}
