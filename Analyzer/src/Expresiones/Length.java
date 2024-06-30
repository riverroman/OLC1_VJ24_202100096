package Expresiones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.*;
import java.util.List;

public class Length extends Instruccion {
    
    private Instruccion valor;
    
    public Length(Instruccion valor, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.valor = valor;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoLength = new NodoAst("Length");
        nodoLength.agregarHijo("length");
        nodoLength.agregarHijo("(");
        nodoLength.agregarHijoAST(valor.astNodo());
        nodoLength.agregarHijo(")");
        return nodoLength;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorInterpretado = valor.interpretar(arbol, tabla);
        
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }
        
        // Verificar si valorInterpretado es una cadena
        if (valorInterpretado instanceof String) {
            String cadena = (String) valorInterpretado;
            return cadena.length();
        } else if (valorInterpretado instanceof java.util.List) {
            java.util.List<?> lista = (java.util.List<?>) valorInterpretado;
            return lista.size();
        }else if(valorInterpretado instanceof Object[]){
            Object[] vector = (Object[]) valorInterpretado;
            return vector.length;
        }else if(valorInterpretado instanceof Object[][]){
            Object[][] vectorBidimensional = (Object[][]) valorInterpretado;
            return  vectorBidimensional.length;
        }else {
            return new Errores("SEMANTICO", "Dato no compatible para obtener su longitud", this.linea, this.columna);
        }
    }
}
