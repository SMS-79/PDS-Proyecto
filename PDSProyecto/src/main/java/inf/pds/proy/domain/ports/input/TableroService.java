package inf.pds.proy.domain.ports.input;

import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Usuario;

public interface TableroService {
	
	Tablero crearTablero(String nombre, Usuario propietario, String url);
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(Long id);
	Optional<Tablero> filtrarTableroByURL(String url);
	void eliminarTablero(Tablero tablero);
	void eliminarTablero(Long id);

}
