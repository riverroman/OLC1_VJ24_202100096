package Expresiones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.*;
import java.util.List;

public class Find extends Instruccion {
    
    private String id;
    private Instruccion expresion;
    
    public Find(String id, Instruccion expresion, int linea, int columna){
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.id = id;
        this.expresion = expresion;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoFind = new NodoAst("Find");
        nodoFind.agregarHijo(id);
        nodoFind.agregarHijo(".");
        nodoFind.agregarHijo("find");
        nodoFind.agregarHijo("(");
        nodoFind.agregarHijoAST(expresion.astNodo());
        nodoFind.agregarHijo(")");
        return nodoFind;
    }
    
    @Override
    public Object interpretar(Arbol arbol , tablaSimbolos tabla){
        Simbolo simbolo = tabla.getVariable(id);
        
        if(simbolo == null){
            return new Errores("SEMANTICO", "El id con nombre: " +  id + " no existe", this.linea, this.columna);
        }
        
        Object valorExpresion = expresion.interpretar(arbol, tabla);
        if(valorExpresion instanceof Errores){
            return valorExpresion;
        }
        
        // Manejar si existe o no el elemento en la lista o vector
        Object valor = simbolo.getValor();
        if (valor instanceof List) {
            List<Object> lista = (List<Object>) valor;
            return lista.contains(valorExpresion);
        } else if (valor instanceof Object[]) {
            Object[] vector = (Object[]) valor;
            for (Object elemento : vector) {
                if (elemento.equals(valorExpresion)) {
                    return true;
                }
            }
            return false;
        } else {
            return new Errores("SEMANTICO", "Error en parametros para la función Find()", this.linea, this.columna);
        }
    }
}