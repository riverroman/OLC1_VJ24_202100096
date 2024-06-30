package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import java.util.LinkedList;
import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import java.util.HashMap;

public class Metodo extends Instruccion{
        
    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;
    
    public Metodo(String id,LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int columna){
        super(tipo, linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoMetodo = new NodoAst("Metodo");
        nodoMetodo.agregarHijo("ID: " + id);
        nodoMetodo.agregarHijo("(");

        NodoAst nodoParametros = new NodoAst("Parametros");
        for (HashMap parametro : parametros) {
            parametro.forEach((key, value) -> {
                nodoParametros.agregarHijo(key.toString() + " : " + value.toString());
            });
        }
        nodoMetodo.agregarHijoAST(nodoParametros);
        nodoMetodo.agregarHijo(")");
        nodoMetodo.agregarHijo("{");

        NodoAst nodoInstrucciones = new NodoAst("Instrucciones");
        for (Instruccion instruccion : instrucciones) {
            nodoInstrucciones.agregarHijoAST(instruccion.astNodo());
        }
        nodoMetodo.agregarHijoAST(nodoInstrucciones);
        nodoMetodo.agregarHijo("}");

        return nodoMetodo;
    }


    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        for(var i : this.instrucciones){
            var resultado = i.interpretar(arbol, tabla);
            
            if(resultado instanceof Errores){
                return resultado;
            }
            
            // Instancia el return
            if(resultado instanceof Expresiones.Nativo){
                return resultado;
            }
            
        }
        return  null;
    }
}