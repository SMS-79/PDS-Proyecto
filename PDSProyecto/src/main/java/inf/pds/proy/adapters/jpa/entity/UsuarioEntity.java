package inf.pds.proy.adapters.jpa.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class UsuarioEntity {
	
	@Id
	private Long id;
	
	private String nombre;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy="propietario")
	private List<TableroEntity> tableros;

	public UsuarioEntity() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<TableroEntity> getTableros() {
		return tableros;
	}

	public void setTableros(List<TableroEntity> tableros) {
		this.tableros = tableros;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nombre, password, tableros);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UsuarioEntity))
			return false;
		UsuarioEntity other = (UsuarioEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password)
				&& Objects.equals(tableros, other.tableros);
	}
	
}