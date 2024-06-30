package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Simbolo.*;
import Simbolo.tablaSimbolos;


public class Break extends Instruccion {
    
    public Break(int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoBreak = new NodoAst("Break");
        nodoBreak.agregarHijo(";");
        return nodoBreak;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        return null;
    }
}
