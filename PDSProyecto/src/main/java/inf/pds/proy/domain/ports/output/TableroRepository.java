package inf.pds.proy.domain.ports.output;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.TableroId;

public interface TableroRepository {

	Tablero guardarTablero(Tablero tablero);
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(TableroId id);
	Optional<Tablero> filtrarTableroByURL(URL url);
	void eliminarTablero(Tablero tablero);
	void eliminarTablero(TableroId id);
	
}
