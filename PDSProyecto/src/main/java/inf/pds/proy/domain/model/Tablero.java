package inf.pds.proy.domain.model;

import java.util.List;
import java.util.UUID;

public class Tablero {
	
	
	
	private UUID id;
	private String nombre;
	
	private List<Tarea> tareas;
	private List<?> historialOp;
	
	private List<Tarea> toDoList;
	private List<Tarea> doingList;
	private List<Tarea> completedList;
	
	
	
	

}
