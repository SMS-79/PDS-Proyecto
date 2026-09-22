package inf.pds.proy.adapters.rest.dto;

import java.util.Objects;

import jakarta.validation.constraints.*;

public class UsuarioDTO {


    private Long id;

    @NotBlank (message = "El nombre del usuario no puede estar vacío")
	private String nombre;
    @NotBlank (message = "El email del usuario no puede estar vacío")
	private String email;
    @NotBlank (message = "La contraseña del usuario no puede estar vacía")
	private String pswd;
	
	public UsuarioDTO() {
		
	}

    public UsuarioDTO(Long id, String nombre, String email, String pswd) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.pswd = pswd;
	}

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

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
	public int hashCode() {
		return Objects.hash(id, nombre, email, pswd);
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UsuarioDTO))
            return false;
        UsuarioDTO other = (UsuarioDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
                && Objects.equals(email, other.email) && Objects.equals(pswd, other.pswd);      
        
    }

    @Override
    public String toString() {
        return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", email=" + email + ", pswd=" + pswd + "]";
    }
}
