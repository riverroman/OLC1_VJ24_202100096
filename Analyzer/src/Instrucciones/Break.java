package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Simbolo.tablaSimbolos;


public class Break extends Instruccion {
    
    public Break(int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        return null;
    }
}
