package inf.pds.proy.domain.ports.output;

import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.Tablero;

public interface TableroRepository {

	Tablero guardarTablero(Tablero tablero);
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(Long id);
	Optional<Tablero> filtrarTableroByURL(String url);
	void eliminarTablero(Tablero tablero);
	void eliminarTablero(Long id);
	
}
