package Abstracto;

import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;

public abstract class Instruccion {
    
    public Tipo tipo;
    public int linea;
    public int columna;
    
   
    public Instruccion(Tipo tipo, int linea, int columna){
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }
    
    public abstract Object interpretar(Arbol arbol, tablaSimbolos tabla);
    
}
