package inf.pds.proy.domain.tablero.listaTareas.tarjetas;

public class TarjetaChecklist {

    private Etiqueta etiqueta; 
    private boolean completada;
    private Map<String, boolean> items;  

    public TarjetaChecklist(Etiqueta etiqueta, boolean completada){
        this.etiqueta = etiqueta; 
        this.completada = false;
        this.items = new Map<String, boolean> items;  
    }

    public void addItem(String i){
        items.put(i, false); 
    }
    @Override
    public void marcarCompletada(){
        completada = true; 
    }
    
    public void itemCompletado(String i){
        items.put(i, true);
    }

    @Override
    public void setEtiqueta(Etiqueta e){
        this.etiqueta = e; 
    }

    @Override
    public Etiqueta getEtiqueta(){
        return this.etiqueta; 
    }

    public boolean isCompletada({
        return completada;
    })



}
