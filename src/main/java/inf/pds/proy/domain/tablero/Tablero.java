package inf.pds.proy.domain.tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import inf.pds.proy.domain.tablero.listaTareas.ListaTareas;
import inf.pds.proy.domain.tablero.listaTareas.tarjetas.Tarjeta;

public class Tablero {

	private final List<ListaTareas> tareas;
	private final List<Tarjeta> tarjetasCompletadas;

	public Tablero() {
		this.tareas = new ArrayList<>();
		this.tarjetasCompletadas = new ArrayList<>();
	}

	public void addLista(ListaTareas lista) {
		this.tareas.add(lista);
	}

	public List<ListaTareas> getTareas() {
		return tareas;
	}

	public List<Tarjeta> getTarjetasCompletadas() {
		return tarjetasCompletadas;
	}
}
