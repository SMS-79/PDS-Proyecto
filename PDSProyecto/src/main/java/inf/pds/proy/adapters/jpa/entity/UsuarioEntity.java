package inf.pds.proy.adapters.jpa.entity;

import inf.pds.proy.domain.model.UsuarioId;
import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UsuarioId id;
	
	private String nombre;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	public UsuarioEntity() {}

	public UsuarioId getId() {
		return id;
	}

	public void setId(UsuarioId id) {
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
	
	
	
	
}
