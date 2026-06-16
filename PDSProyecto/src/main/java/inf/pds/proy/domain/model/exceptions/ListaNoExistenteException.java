package inf.pds.proy.domain.model.exceptions;

public class ListaNoExistenteException extends Exception {

	public ListaNoExistenteException(String mensaje) {
		super(mensaje);
	}
	
	public ListaNoExistenteException(String mensaje, Exception e) {
		super(mensaje, e);
	}
}
