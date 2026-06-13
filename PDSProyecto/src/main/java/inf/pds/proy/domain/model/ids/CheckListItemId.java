package inf.pds.proy.domain.model.ids;

import java.util.Objects;
import java.util.Random;

public class CheckListItemId {
	private Long codigo;
	
	private static Random rand;

	
	public static class IdentificadorItemException extends Exception {
		private static final long serialVersionUID = 4944813248848099L;
		
		public IdentificadorItemException(String mensaje) {
			super(mensaje);
		}
		
		public IdentificadorItemException(String mensaje, Exception ex) {
			super(mensaje, ex);
		}
	}
	
	
	public CheckListItemId(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public static CheckListItemId of(Long codigo) throws IdentificadorItemException{
		if(codigo == null) {
			throw new IdentificadorItemException("El codigo no puede ser nulo");
		}
		
		return new CheckListItemId(codigo);
	}
	
	public static CheckListItemId random() {
		return new CheckListItemId(rand.nextLong());
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
		if (!(obj instanceof CheckListItemId))
			return false;
		CheckListItemId other = (CheckListItemId) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
}
