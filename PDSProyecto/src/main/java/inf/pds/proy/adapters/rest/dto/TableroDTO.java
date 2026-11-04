package inf.pds.proy.adapters.rest.dto;

import java.util.Objects;

import jakarta.validation.constraints.*;

public class TableroDTO {

	private Long id;
	
    @NotBlank(message = "El nombre del tablero no puede estar vacío")
	private String nombre;
	
    @NotNull(message = "El propietario del tablero no puede ser nulo")
	private UsuarioDTO propietario; 

    @NotNull(message = "La URL del tablero no puede ser nula")
	private String url; 

    public TableroDTO() {
    }

    public TableroDTO(Long id, String nombre, UsuarioDTO propietario, String url) {
        this.id = id;
        this.nombre = nombre;
        this.propietario = propietario;
        this.url = url;
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

    public UsuarioDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioDTO propietario) {
        this.propietario = propietario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, propietario, url);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TableroDTO))
            return false;
        TableroDTO other = (TableroDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
                && Objects.equals(propietario, other.propietario) && Objects.equals(url, other.url);
    }

    @Override
    public String toString() {
        return "TableroDTO [id=" + id + ", nombre=" + nombre + ", propietario=" + propietario + ", url=" + url + "]";
    }
    
}
