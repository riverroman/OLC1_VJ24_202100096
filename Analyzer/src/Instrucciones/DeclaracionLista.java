package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
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
    public NodoAst astNodo() {
        NodoAst nodoDeclaracion = new NodoAst("Declaración de Lista");
        nodoDeclaracion.agregarHijo("List<");
        nodoDeclaracion.agregarHijo(tipo.toString());
        nodoDeclaracion.agregarHijo(">");
        nodoDeclaracion.agregarHijo(identificador);
        nodoDeclaracion.agregarHijo("=");
        nodoDeclaracion.agregarHijo("new");
        nodoDeclaracion.agregarHijo("List");
        nodoDeclaracion.agregarHijo("(");
        nodoDeclaracion.agregarHijo(")");
        nodoDeclaracion.agregarHijo(";");
        return nodoDeclaracion;
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
