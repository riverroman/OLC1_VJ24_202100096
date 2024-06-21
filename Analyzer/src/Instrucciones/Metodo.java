package Instrucciones;

import Abstracto.Instruccion;
import java.util.LinkedList;
import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;

public class Metodo extends Instruccion{
        
    public String id;
    
    public LinkedList<Instruccion> instrucciones;
    
    public Metodo(String id, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int columna){
        super(tipo, linea, columna);
        this.id = id;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        for(var i : this.instrucciones){
            var resultado = i.interpretar(arbol, tabla);
        }
        return  null;
    }
}
