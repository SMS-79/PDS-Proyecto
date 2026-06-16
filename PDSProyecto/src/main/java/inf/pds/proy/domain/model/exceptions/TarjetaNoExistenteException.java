package inf.pds.proy.domain.model.exceptions;

public class TarjetaNoExistenteException extends Exception{
	
	public TarjetaNoExistenteException(String mensaje) {
		super(mensaje);
	}
	
	public TarjetaNoExistenteException(String mensaje, Exception e) {
		super(mensaje, e);
	}
}
