package inf.pds.proy.application.usecases;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.ports.input.TableroService;
import inf.pds.proy.domain.ports.output.TableroRepository;

@Service
public class TableroServiceImpl implements TableroService{

	private TableroRepository repTab;
	
	public TableroServiceImpl(TableroRepository rep) {
		this.repTab = rep;
	}

	@Override
	public Tablero crearTablero(String nombre, Usuario propietario, String url) {
		Tablero table = new Tablero(new Random().nextLong(), nombre, propietario, url);
		repTab.guardarTablero(table);
		return table;
	}

	@Override
	public List<Tablero> obtenerTableros() {
		return repTab.obtenerTableros();
	}

	@Override
	public Optional<Tablero> filtrarTableroById(Long id) {
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
	public void eliminarTablero(Long id) {
		repTab.eliminarTablero(id);
	}
	
	
	
	
}
