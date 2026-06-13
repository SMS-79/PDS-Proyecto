package inf.pds.proy.domain.model.ids;

import java.util.Random;


public class HistorialOpsId {
	private Long codigo;
	
	private static Random rand;
	
	public static class IdentificadorHistorialException extends Exception {
		private static final long serialVersionUID = 4944813248848099L;
		
		public IdentificadorHistorialException(String mensaje) {
			super(mensaje);
		}
		
		public IdentificadorHistorialException(String mensaje, Exception ex) {
			super(mensaje, ex);
		}
	}
	
	
	public HistorialOpsId(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public static HistorialOpsId of(Long codigo) throws IdentificadorHistorialException{
		if(codigo == null) {
			throw new IdentificadorHistorialException("El codigo no puede ser nulo");
		}
		
		return new HistorialOpsId(codigo);
	}
	
	public static HistorialOpsId random() {
		return new HistorialOpsId(rand.nextLong());
	}
	
	
	public Long getId() {
		return this.codigo;
	}
}
