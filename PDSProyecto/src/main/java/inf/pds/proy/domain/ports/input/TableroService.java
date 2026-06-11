package inf.pds.proy.domain.ports.input;

import java.util.List;
import java.util.Optional;

import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.ListaTareasId;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.TableroId;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.TableroId.IdentificadorTableroException;

public interface TableroService {
	
	Tablero crearTablero(String nombre, Usuario propietario, String url) throws IdentificadorTableroException;
	ListaTareas crearLista(Tablero tablero, String tipo);
	List<Tablero> obtenerTableros();
	Optional<Tablero> filtrarTableroById(TableroId id);
	Optional<Tablero> filtrarTableroByURL(String url);
	List<ListaTareas> obtenerListas(Tablero tablero);
	Optional<ListaTareas> filtrarListaById(Tablero tablero, ListaTareasId id);
	void eliminarTablero(Tablero tablero);
	void eliminarTablero(TableroId id);
	void eliminarLista(Tablero tablero, ListaTareas lista);
}
