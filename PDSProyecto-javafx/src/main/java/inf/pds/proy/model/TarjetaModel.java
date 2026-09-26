package inf.pds.proy.model;

public class TarjetaModel {

    private IdentificadorModel id;
    private String titulo;
    private String descripcion;

    public TarjetaModel() {
    }

    public IdentificadorModel getId() {
        return id;
    }

    public void setId(IdentificadorModel id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
