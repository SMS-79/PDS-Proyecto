package inf.pds.proy.domain.model.ids;

import java.util.Objects;
import java.util.Random;

public class UsuarioId {
	
	private Long codigo;
	
	private static Random rand;

	
	public static class IdentificadorUsuarioException extends Exception {
		private static final long serialVersionUID = 4944813248848099L;
		
		public IdentificadorUsuarioException(String mensaje) {
			super(mensaje);
		}
		
		public IdentificadorUsuarioException(String mensaje, Exception ex) {
			super(mensaje, ex);
		}
	}
	
	
	public UsuarioId(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public static UsuarioId of(Long codigo) throws IdentificadorUsuarioException{
		if(codigo == null) {
			throw new IdentificadorUsuarioException("El codigo no puede ser nulo");
		}
		
		return new UsuarioId(codigo);
	}
	
	public static UsuarioId random() {
		return new UsuarioId(rand.nextLong());
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
		UsuarioId other = (UsuarioId) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	
}
