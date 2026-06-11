package inf.pds.proy.domain.model;

import java.util.Objects;
import java.util.Random;

public class ListaTareasId {
	
	private Long codigo;

	private static Random rand;
	
	public static class IdentificadorListaException extends Exception {
		private static final long serialVersionUID = 4944813248848099L;
		
		public IdentificadorListaException(String mensaje) {
			super(mensaje);
		}
		
		public IdentificadorListaException(String mensaje, Exception ex) {
			super(mensaje, ex);
		}
	}
	
	
	public ListaTareasId(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public static ListaTareasId of(Long codigo) throws IdentificadorListaException{
		if(codigo == null) {
			throw new IdentificadorListaException("El codigo no puede ser nulo");
		}
		
		return new ListaTareasId(codigo);
	}
	
	public static ListaTareasId random() {
		return new ListaTareasId(rand.nextLong());
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
		if (!(obj instanceof ListaTareasId))
			return false;
		ListaTareasId other = (ListaTareasId) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
