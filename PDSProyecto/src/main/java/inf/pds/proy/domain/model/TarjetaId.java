package inf.pds.proy.domain.model;

import java.util.Objects;
import java.util.Random;

public class TarjetaId {
	
	private Long codigo;
	
	private static Random rand;

	
	public static class IdentificadorTarjetaException extends Exception {
		private static final long serialVersionUID = 4944813248848099L;
		
		public IdentificadorTarjetaException(String mensaje) {
			super(mensaje);
		}
		
		public IdentificadorTarjetaException(String mensaje, Exception ex) {
			super(mensaje, ex);
		}
	}
	
	
	public TarjetaId(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public static TarjetaId of(Long codigo) throws IdentificadorTarjetaException{
		if(codigo == null) {
			throw new IdentificadorTarjetaException("El codigo no puede ser nulo");
		}
		
		return new TarjetaId(codigo);
	}
	
	public static TarjetaId random() {
		return new TarjetaId(rand.nextLong());
	}
	
	public Long getId() {
		return this.codigo;
	}


	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TarjetaId))
			return false;
		TarjetaId other = (TarjetaId) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
