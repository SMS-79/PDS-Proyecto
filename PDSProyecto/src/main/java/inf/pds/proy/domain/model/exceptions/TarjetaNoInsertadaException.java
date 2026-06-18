package inf.pds.proy.domain.model.exceptions;

public class TarjetaNoInsertadaException extends Exception{
	public TarjetaNoInsertadaException(String mensaje) {
		super(mensaje);
	}
	
	public TarjetaNoInsertadaException(String mensaje, Exception e) {
		super(mensaje, e);
	}
}
