package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.*;

public class Print extends Instruccion{
    
    private Instruccion expresion;
    
    public Print(Instruccion expresion, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.expresion = expresion;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var resultado = this.expresion.interpretar(arbol, tabla);
        
        if (resultado instanceof Errores) {
            return resultado;
        }
        
        if (resultado instanceof Object[]) {
            Object[] vector = (Object[]) resultado;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < vector.length; i++) {
                sb.append(vector[i].toString());
                if (i < vector.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            arbol.Print(sb.toString());
        } else {
            arbol.Print(resultado.toString());
        }
        return null;
    }
   
}
