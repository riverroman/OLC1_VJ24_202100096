package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.*;
import java.util.LinkedList;

public class While extends Instruccion {
    
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public While(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoWhile = new NodoAst("While");

        nodoWhile.agregarHijo("while");
        nodoWhile.agregarHijo("(");
        nodoWhile.agregarHijoAST(this.condicion.astNodo());
        nodoWhile.agregarHijo(")");

        NodoAst nodoInstrucciones = new NodoAst("Instrucciones-While");
        for (Instruccion instruccion : this.instrucciones) {
            nodoInstrucciones.agregarHijoAST(instruccion.astNodo());
        }
        nodoWhile.agregarHijo("{");
        nodoWhile.agregarHijoAST(nodoInstrucciones);
        nodoWhile.agregarHijo("}");
        
        return nodoWhile;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condicional = this.condicion.interpretar(arbol, tabla);
        if (condicional instanceof Errores) {
            return condicional;
        }

        if (this.condicion.getTipo().getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La expresión a evaluar no es de tipo booleano", this.linea, this.columna);
        }

        while ((boolean) this.condicion.interpretar(arbol, tabla)) {
            // Crear un nuevo entorno
            var newTabla = new tablaSimbolos(tabla);

            for (var instruccion : this.instrucciones) {
                var resultado = instruccion.interpretar(arbol, newTabla);

                if (resultado instanceof Errores) {
                    arbol.getErrores().add((Errores) resultado);
                }

                if (resultado instanceof Break) {
                    return null;
                }
                
                if (resultado instanceof Continue) {
                    break;
                }
                
                if (resultado instanceof Expresiones.Nativo){
                    return resultado;
                }
                
            }
            
            // Evaluar la condicion nuevamente *Importante
            condicional = this.condicion.interpretar(arbol, tabla);
            if (condicional instanceof Errores) {
                return condicional;
            }
        }
        return null;
    }
}
