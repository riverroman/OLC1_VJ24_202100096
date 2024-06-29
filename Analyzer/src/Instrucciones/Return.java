package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.Arbol;
import Simbolo.tablaSimbolos;

public class Return extends Instruccion {
    private Instruccion valorRetorno;

    public Return(int linea, int columna) {
        super(null, linea, columna);
        this.valorRetorno = null;
    }
    
    public Return(Instruccion valorRetorno, int linea, int columna) {
        super(null, linea, columna);
        this.valorRetorno = valorRetorno;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        
    }
}
