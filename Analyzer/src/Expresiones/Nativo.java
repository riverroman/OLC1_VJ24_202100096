package Expresiones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;

public class Nativo extends Instruccion {
    
    public Object valor;
    
    public Nativo(Object valor, Tipo tipo, int linea, int columna){
        super(tipo, linea, columna);
        this.valor = valor;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoNativo = new NodoAst("Nativo");
        
        String valorFormateado;
        if (valor instanceof String) {
            valorFormateado = "\"" + valor.toString() + "\"";
        } else {
            valorFormateado = valor.toString();
        }
        
        nodoNativo.agregarHijo(valorFormateado);
        return nodoNativo;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        return valor;
    }
    
    public Object getValor() {
        return valor;
    }

    @Override
    public Tipo getTipo() {
        return this.tipo;
    }
    
}