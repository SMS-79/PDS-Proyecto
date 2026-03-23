package inf.pds.proy.domain.tablero.listaTareas.tarjetas;

public class Etiqueta {

    private String nombre; 
    private String color;  // se van a meter en hexadecimal

    public Etiqueta(String nombre, String color){
        this.nombre = nombre;
        this.color = color; 
    }

    public String getNombre(){
        return nombre; 
    }

    public String getColor(){
        return color; 
    }
}
