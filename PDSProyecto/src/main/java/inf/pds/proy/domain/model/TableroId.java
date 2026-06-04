package inf.pds.proy.domain.model;

import java.util.Objects;

public class TableroId {
	private Long codigo;

	
	public static class IdentificadorTableroException extends Exception {
		private static final long serialVersionUID = 4944813248848099L;
		
		public IdentificadorTableroException(String mensaje) {
			super(mensaje);
		}
		
		public IdentificadorTableroException(String mensaje, Exception ex) {
			super(mensaje, ex);
		}
	}
	
	
	public TableroId(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public static TableroId of(Long codigo) throws IdentificadorTableroException{
		if(codigo == null) {
			throw new IdentificadorTableroException("El codigo no puede ser nulo");
		}
		
		return new TableroId(codigo);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableroId other = (TableroId) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}
