package inf.pds.proy.domain.model;

import java.util.Objects;

public record Etiqueta(String nombre, String color) {

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Etiqueta))
			return false;
		Etiqueta other = (Etiqueta) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
