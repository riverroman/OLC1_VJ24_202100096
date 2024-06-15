package Simbolo;

import java.util.HashMap;

public class tablaSimbolos {
    
    private tablaSimbolos tablaAnterior;
    private HashMap<String, Simbolo> tablaActual;
    private String nombre;
    
    public tablaSimbolos(){
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }
    
    public tablaSimbolos(tablaSimbolos tablaAnterior){
        this.tablaAnterior = tablaAnterior;
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public tablaSimbolos getTablaAnterior() {
        return tablaAnterior;
    }

    public HashMap<String, Simbolo> getTablaActual() {
        return tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTablaAnterior(tablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    public void setTablaActual(HashMap<String, Simbolo> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean setVariable(Simbolo simbolo){
        Simbolo busqueda = this.tablaActual.get(simbolo.getId().toLowerCase());
        if(busqueda == null){
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            return true;
        }
        return  false;
    }
    
    public Simbolo getVariable(String id){
        Simbolo busqueda = this.tablaActual.get(id.toLowerCase());
        if(busqueda != null){
            return  busqueda;
        } else if (this.tablaAnterior != null) {
            return this.tablaAnterior.getVariable(id);
        }
        return  null;
    }
}
