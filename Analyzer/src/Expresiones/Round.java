package Expresiones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.*;

public class Round extends Instruccion {
   
    private Instruccion valor;
  
    public Round(Instruccion valor, int linea, int columna){
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.valor = valor;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoRound = new NodoAst("Round");
        nodoRound.agregarHijo("Round");
        nodoRound.agregarHijo("(");
        nodoRound.agregarHijoAST(valor.astNodo());
        nodoRound.agregarHijo(")");
        return nodoRound;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        
        Object valorInterpretado = valor.interpretar(arbol, tabla);
        
        if(valorInterpretado instanceof Errores){
            return valorInterpretado;
        }
        
        if(valor.getTipo().getTipo() != tipoDato.DECIMAL){
            return new Errores("SEMANTICO", "El dato a redondear no es decimal: " + valorInterpretado, this.linea, this.columna);
        }
        
        double numero = (double) valorInterpretado;
        int resultado = (int) Math.round(numero);
        
        return resultado;
    }
    
}
