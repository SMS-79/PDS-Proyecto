package inf.pds.proy.application.usecases;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.TableroId;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.TableroId.IdentificadorTableroException;
import inf.pds.proy.domain.ports.input.TableroService;
import inf.pds.proy.domain.ports.output.TableroRepository;

public class TableroServiceImpl implements TableroService{

	private TableroRepository repTab;
	
	public TableroServiceImpl(TableroRepository rep) {
		this.repTab = rep;
	}

	@Override
	public Tablero crearTablero(String nombre, Usuario propietario, URL url) {
		try {
			Tablero table = new Tablero(TableroId.of(new Random().nextLong()), nombre, propietario, url);
			repTab.guardarTablero(table);
			return table;
		}catch(IdentificadorTableroException e) {
			
		}
		return null;
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
	public Optional<Tablero> filtrarTableroByURL(URL url) {
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
	
	
	
	
}
