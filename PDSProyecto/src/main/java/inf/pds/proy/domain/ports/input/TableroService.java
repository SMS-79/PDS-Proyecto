package inf.pds.proy.domain.ports.input;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.TableroId;
import inf.pds.proy.domain.model.Usuario;

public interface TableroService {
	
	Tablero crearTablero(String nombre, Usuario propietario, URL url);
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(TableroId id);
	Optional<Tablero> filtrarTableroByURL(URL url);
	void eliminarTablero(TableroId id);

}
