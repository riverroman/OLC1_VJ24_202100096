
package Simbolo;

import Abstracto.Instruccion;
import Excepciones.Errores;
import java.util.LinkedList;
import Instrucciones.Metodo;

public class Arbol {
    
       private LinkedList<Instruccion> instrucciones;
       private String consola;
       private tablaSimbolos tablaGlobal;
       private LinkedList<Errores> errores;
       private LinkedList<Instruccion> funciones;
       
       public Arbol(LinkedList<Instruccion> instrucciones){
           this.instrucciones = instrucciones;
           this.consola = "";
           this.tablaGlobal = new tablaSimbolos();
           this.errores = new LinkedList<>();
           this.funciones = new LinkedList<>();
       }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public tablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public void setTablaGlobal(tablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }
    
    public void Print(String valor){
        this.consola += valor + "\n";
    }
    
    public LinkedList<Instruccion> getFunciones(){
        return funciones;
    }
    
    public void setFunciones(Instruccion funcion){
        this.funciones.add(funcion);
    }
    
    public void addFunciones(Instruccion funcion){
        this.funciones.add(funcion);
    }
    
    public Instruccion getFuncion(String id){
        for(var  i : this.funciones){
            if(i instanceof Metodo metodo){
                if(metodo.id.equalsIgnoreCase(id)){
                    return i;
                }
            }
        }
        return  null;
    }
    
}
