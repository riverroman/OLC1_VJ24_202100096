package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.*;

public class DeclaracionLista extends Instruccion {
    
    private String identificador;
    private Tipo tipo;
    
    public DeclaracionLista(String identificador, Tipo tipo, int linea, int columna) {
        super(new Tipo(tipoDato.LISTA), linea, columna);
        this.identificador = identificador;
        this.tipo = tipo;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        java.util.List<Object> lista = new java.util.ArrayList<>();
        
        // Creo el símbolo para la lista
        Simbolo s = new Simbolo(this.tipo, this.identificador, lista, true);
        
        // Agrego el símbolo a la tabla de variables
        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente: " + identificador, this.linea, this.columna);
        }
        
        return null;
    }
    
}
