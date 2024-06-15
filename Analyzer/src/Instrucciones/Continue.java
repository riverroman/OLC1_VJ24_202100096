package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Simbolo.tablaSimbolos;

public class Continue extends Instruccion {
    
    public Continue(int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        return this;
    }
}
