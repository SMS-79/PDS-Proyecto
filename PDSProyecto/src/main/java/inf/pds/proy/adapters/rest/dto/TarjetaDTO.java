package inf.pds.proy.adapters.rest.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import inf.pds.proy.domain.model.Usuario;

import inf.pds.proy.domain.model.CheckListItem;
import jakarta.validation.constraints.*;

public class TarjetaDTO {

    
	private Long id;

    @NotBlank(message = "El nombre de la tarjeta no puede estar vacío")
	private String nombre;

    @NotBlank(message = "El nombre de la etiqueta no puede estar vacío") 
	private String etiquetaNombre;
    @NotBlank (message = "El color de la etiqueta no puede estar vacío")
	private String etiquetaColor;
	
    @NotNull (message = "La fecha límite no puede ser nula")
	private LocalDate fechaLimite; 

    @NotNull (message = "El responsable de la tarjeta no puede ser nulo")
	private Usuario responsable;

    @NotNull (message = "El tipo de tarjeta no puede ser nulo")
    private TipoTarjeta tipoTarjeta;

    //TarjetaTarea
    private String descripcion; 

    //TarjetaCheckList
    private List<CheckListItem> items;

    public TarjetaDTO() {
    }


    public TarjetaDTO(Long id, String nombre, String etiquetaNombre, String etiquetaColor, LocalDate fechaLimite,
            Usuario responsable, TipoTarjeta tipoTarjeta, String descripcion, List<CheckListItem> items) {
        this.id = id;
        this.nombre = nombre;
        this.etiquetaNombre = etiquetaNombre;
        this.etiquetaColor = etiquetaColor;
        this.fechaLimite = fechaLimite;
        this.responsable = responsable;
        this.tipoTarjeta = tipoTarjeta;
        this.descripcion = descripcion;
        this.items = items;
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

    public String getEtiquetaNombre() {
        return etiquetaNombre;
    }

    public void setEtiquetaNombre(String etiquetaNombre) {
        this.etiquetaNombre = etiquetaNombre;
    }

    public String getEtiquetaColor() {
        return etiquetaColor;
    }

    public void setEtiquetaColor(String etiquetaColor) {
        this.etiquetaColor = etiquetaColor;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CheckListItem> getItems() {
        return items;
    }

    public void setItems(List<CheckListItem> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, etiquetaNombre, etiquetaColor, fechaLimite, responsable, tipoTarjeta, descripcion, items);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TarjetaDTO))
            return false;
        TarjetaDTO other = (TarjetaDTO) obj;
        
        return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
                && Objects.equals(etiquetaNombre, other.etiquetaNombre) && Objects.equals(etiquetaColor, other.etiquetaColor)
                && Objects.equals(fechaLimite, other.fechaLimite) && Objects.equals(responsable, other.responsable)
                && Objects.equals(tipoTarjeta, other.tipoTarjeta) && Objects.equals(descripcion, other.descripcion)
                && Objects.equals(items, other.items);
    }

    @Override
    public String toString() {
        return "TarjetaDTO [id=" + id + ", nombre=" + nombre + ", etiquetaNombre=" + etiquetaNombre + ", etiquetaColor="
                + etiquetaColor + ", fechaLimite=" + fechaLimite + ", responsable=" + responsable + ", tipoTarjeta=" + tipoTarjeta + ", descripcion="
                + descripcion + ", items=" + items + "]";
    }

    

    

    
}
