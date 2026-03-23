package inf.pds.proy.domain.tablero.listaTareas.tarjetas;

public class TarjetaTarea implements Tarjeta{


    private Etiqueta etiqueta;
    private boolean completada;

    public TarjetaTarea(Etiqueta etiqueta, boolean completada){
        this.etiqueta = etiqueta;
        this.completada = false; 
    }

    @Override
    public void setEtiqueta(Etiqueta e){
        this.etiqueta = e; 
    }

    @Override
    public Etiqueta getEtiqueta(){
        return this.etiqueta; 
    }

    @Override
    public void marcarCompletada(){
        this.completada = true; 
    }

    public boolean isCompletada(){
        return completada;
    }

}
