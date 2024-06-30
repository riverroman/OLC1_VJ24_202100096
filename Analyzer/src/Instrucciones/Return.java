package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Expresiones.Nativo;
import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import Simbolo.tipoDato;

public class Return extends Instruccion {
    private Instruccion valorRetorno;
    
    public Return(Instruccion valorRetorno, int linea, int columna) {
        super(null, linea, columna);
        this.valorRetorno = valorRetorno;
    }

    @Override
    public NodoAst astNodo() {
        NodoAst nodoReturn = new NodoAst("Return");
        nodoReturn.agregarHijo("return");
        if (valorRetorno != null) {
            nodoReturn.agregarHijoAST(valorRetorno.astNodo());
        }
        nodoReturn.agregarHijo(";");
        return nodoReturn;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        
        if (valorRetorno != null) {
            Object valor = this.valorRetorno.interpretar(arbol, tabla);
            if (valor instanceof Errores) {
                return valor;
            }
            return new Nativo(valor, new Tipo(tipoDato.DECIMAL),this.linea, this.columna);
        }
        return new Nativo("null", new Tipo(tipoDato.VOID), this.linea, this.columna);
         
    }
}