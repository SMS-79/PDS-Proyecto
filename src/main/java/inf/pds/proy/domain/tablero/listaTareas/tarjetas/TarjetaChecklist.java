package inf.pds.proy.domain.tablero.listaTareas.tarjetas;

import java.util.HashMap;
import java.util.Map;

public class TarjetaChecklist implements Tarjeta {

    private Etiqueta etiqueta; 
    private boolean completada;
    private Map<String, Boolean> items;  

    public TarjetaChecklist(Etiqueta etiqueta, boolean completada){
        this.etiqueta = etiqueta; 
        this.completada = completada;
        this.items = new HashMap<>();  
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

    public boolean isCompletada(){
        return completada;
    }



}
