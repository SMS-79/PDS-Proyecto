package inf.pds.proy.domain.model;

import java.util.List;

import inf.pds.proy.domain.model.ids.UsuarioId;

public class Usuario {
	
	private UsuarioId id;
	private String nombre;
	private String email;
	private String pswd;
	
	private List<Tablero> tableros;
	private List<Tablero> tablerosCompartidos;
	
	public Usuario() {
		
	}
	
	public Usuario(UsuarioId id, String nombre, String email, String pswd) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.pswd = pswd;
	}
	

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

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public List<Tablero> getTableros() {
		return tableros;
	}

	public void setUrl(List<Tablero> tableros) {
		this.tableros = tableros;
	}

}
