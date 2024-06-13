
package Simbolo;

import java.util.HashMap;

public class tablaSimbolos {
    
    private tablaSimbolos tablaAnterior;
    private HashMap<String, Object> tablaActual;
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

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTablaAnterior(tablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean setVariable(Simbolo simbolo){
        Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase());
        if(busqueda == null){
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            return true;
        }
        return  false;
    }
    
    public Simbolo getVariable(String id){
        Simbolo busqueda = (Simbolo) this.tablaActual.get(id.toLowerCase());
        if(busqueda != null){
            return  busqueda;
        }
        return  null;
    }
    
}
