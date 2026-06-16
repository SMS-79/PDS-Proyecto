package inf.pds.proy.domain.model.exceptions;

public class TableroNoExistenteException extends Exception {

	public TableroNoExistenteException(String mensaje) {
		super(mensaje);
	}
	
	public TableroNoExistenteException(String mensaje, Exception e) {
		super(mensaje, e);
	}
}
