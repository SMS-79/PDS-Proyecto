package inf.pds.proy.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;

import inf.pds.proy.domain.model.HistorialOps.TipoOperacion;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.ListaTareasId.IdentificadorListaException;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.model.ids.TarjetaId;
import inf.pds.proy.domain.model.exceptions.*;

public class Tablero {
	
	private TableroId id;
	private String nombre;
	private Usuario propietario;
	private String url;
	private boolean bloqueado; // bloqueo temporal de un tablero que durará como máximo una semana
	private LocalDateTime bloqueoFin;
	private List<HistorialOps> historialOp;
	private List<Usuario> miembros; 
	private List<ListaTareas> listasTareas; // columnas dinámicas tipo (DOING, T0DO, BACKLOG, STOPPED etc...) 
	private ListaTareas listaCompletadas; // lista para separar las completadas
	
	public Tablero() {}
	
	public Tablero(TableroId id, String nombre, Usuario propietario, String url) {
		this.id = id;
		this.nombre = nombre;
		this.propietario = propietario;
		this.url = url;
		this.bloqueado = false;
		this.miembros = new ArrayList<>();
		this.listasTareas = new ArrayList<>();
		this.historialOp = new ArrayList<>();
		try {
			this.listaCompletadas = new ListaTareas("Completadas");
		} catch (IdentificadorListaException e) {
			e.printStackTrace();
		}
	}

	public TableroId getId() {
		return id;
	}

	public void setId(TableroId id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public LocalDateTime getBloqueoFin() {
		return this.bloqueoFin;
	}

	public void setBloqueoFin(LocalDateTime bloqueoFin) {
		this.bloqueoFin = bloqueoFin;
	}

	public List<HistorialOps> getHistorialOp() {
		return historialOp;
	}
	
	public void setHistorialOp(List<HistorialOps> historial) {
		this.historialOp = historial;
	}

	public List<ListaTareas> getListas() {
		return listasTareas;
	}
	
	public void setListas(List<ListaTareas> listaTareas) {
		this.listasTareas = listaTareas;
	}


	public ListaTareas getListaCompletadas() {
		return listaCompletadas;
	}
	
	public void setListaCompletadas(ListaTareas listaCompletadas) {
		this.listaCompletadas = listaCompletadas;
	}
	
	public List<Usuario> getMiembros(){
		return miembros;
	}
	
	public ListaTareas crearLista(String tipo) {
		try {
			ListaTareas listaTareas = new ListaTareas(tipo);
			this.listasTareas.add(listaTareas);
			return listaTareas;
		}catch(IdentificadorListaException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Optional<ListaTareas> obtenerLista(ListaTareasId id) {
		return this.listasTareas.stream()
				.filter(l -> l.getId().equals(id))
				.findFirst();
	}
	
	public void eliminarLista(ListaTareasId listaId) {
		ListaTareas lista = obtenerLista(listaId).orElse(null);
		this.listasTareas.remove(lista);
	}
	
	public void addMiembro(Usuario u) {
		this.miembros.add(u); 
	}
	
	public void registrarOp(TipoOperacion tOp, String descripcion, Usuario user) {
		this.historialOp.add(new HistorialOps(tOp, descripcion, user)); 
	}

	
	
	public void addTarjetaToList(ListaTareasId listaId, Tarjeta tarjeta) throws ListaNoExistenteException{
		ListaTareas lista = obtenerLista(listaId).orElseThrow(() -> new ListaNoExistenteException("Lista con id " + listaId + " no encontrada"));		
		lista.addTarjeta(tarjeta);
	}
	
	public TarjetaTarea crearTarjetaTarea(ListaTareasId listaId, String nombre, Etiqueta etiqueta, LocalDate fechaLimite, Usuario responsable) throws ListaNoExistenteException {
		if(this.bloqueado) {
			throw new IllegalStateException("El tablero está bloqueado, no se pueden añadir tarjetas");
		}
		
		TarjetaTarea tarjeta = new TarjetaTarea(nombre, responsable, etiqueta, fechaLimite);
		
		addTarjetaToList(listaId, tarjeta);
		
		return tarjeta; 
	}
	
	public TarjetaCheckList crearTarjetaCheckList(ListaTareasId listaId, String nombre, Etiqueta etiqueta) throws ListaNoExistenteException{
		if(this.bloqueado) {
			throw new IllegalStateException("El tablero está bloqueado, no se pueden añadir tarjetas");
		}
		
		TarjetaCheckList tarjeta = new TarjetaCheckList(nombre, etiqueta);
		
		addTarjetaToList(listaId, tarjeta);
		
		return tarjeta; 
	}

	public List<Tarjeta> getTarjetasDeLista(ListaTareasId listaId) throws ListaNoExistenteException{
		ListaTareas lista = obtenerLista(listaId).orElseThrow(() -> new ListaNoExistenteException("Lista con id " + listaId + " no encontrada")); 
		return lista.getTarjetas(); 
	}
	
	public List<Tarjeta> getTarjetasPorEtiqueta(String etiq){
		Etiqueta etiquetaFiltro = new Etiqueta(etiq, "");
		return getListas().stream()
					.flatMap(l -> l.getTarjetas().stream())
					.filter(t -> t.getEtiqueta()
									.filter(e -> e.equals(etiquetaFiltro)).isPresent())
					.toList();
					
	}
	
	public Optional<Tarjeta> obtenerTarjetaDeLista(ListaTareasId listaId, TarjetaId tarjetaId) throws ListaNoExistenteException{
		return getTarjetasDeLista(listaId).stream()
									.filter(t -> t.getId().equals(tarjetaId))
									.findFirst();
	}
	
	public void alternarCompletarTarjeta(ListaTareasId listaId, TarjetaId tarjetaId) throws ListaNoExistenteException, TarjetaNoExistenteException{
		Tarjeta tarjeta = obtenerTarjetaDeLista(listaId, tarjetaId).orElseThrow(() -> new TarjetaNoExistenteException("Tarjeta con id " + tarjetaId + " no encontrada"));
		
		tarjeta.setCompletada(!tarjeta.isCompletada());
		if(tarjeta.isCompletada()) {
			listaCompletadas.addTarjeta(tarjeta);
		}
		else {
			listaCompletadas.removeTarjeta(tarjeta);
		}
	}
	
	public void eliminarTarjetaDeLista(ListaTareasId listaId, TarjetaId tarjetaId) throws ListaNoExistenteException, TarjetaNoExistenteException{
		ListaTareas lista = obtenerLista(listaId).orElseThrow(() -> new ListaNoExistenteException("Lista con id " + listaId + " no encontrada")); 
		Tarjeta tarjeta = obtenerTarjetaDeLista(listaId, tarjetaId).orElseThrow(() -> new TarjetaNoExistenteException("Tarjeta con id " + tarjetaId + " no encontrada"));
		lista.removeTarjeta(tarjeta);
		
	}
	
	public void bloquear(LocalDateTime fechaBloqueo) {
		setBloqueado(true);
		setBloqueoFin(fechaBloqueo);
	}
	
	public void desbloquear() {
		setBloqueado(false);
	}
	
	@Scheduled(fixedRate = 60000)
	public void desbloquearTiempo() {
		if(this.getBloqueoFin().isBefore(LocalDateTime.now())) {
			desbloquear();
		}
	}
}
