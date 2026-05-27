package inf.pds.proy.domain.model;

import java.net.URL;
import java.util.List;
import java.util.UUID;

public class Usuario {
	
	private UUID id;
	private String nombre;
	private String email;
	private String pswd;
	
	private List<URL> urlTableros;
	private List<Tablero> tablerosCompartidos;
	
	public Usuario() {
		
	}
	
	public Usuario(UUID id, String nombre, String email, String pswd) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.pswd = pswd;
	}
	
	public Usuario(String nombre, String email, String pswd) {
		this(UUID.randomUUID(), nombre, email, pswd);
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public List<URL> getUrls() {
		return urlTableros;
	}

	public void setUrl(List<URL> url) {
		this.urlTableros = url;
	}
	
	

}
