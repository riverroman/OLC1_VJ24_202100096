package Expresiones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.*;

public class ToString extends Instruccion {
    
    private Instruccion valor;
    
    public ToString(Instruccion valor, int linea, int columna) {
        super(new Tipo(tipoDato.CADENA), linea, columna);
        this.valor = valor;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorInterpretado = valor.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }
        
        if (valor.getTipo().getTipo() == tipoDato.ENTERO) {
            return String.valueOf((int) valorInterpretado);
        } else if (valor.getTipo().getTipo() == tipoDato.DECIMAL) {
            return String.valueOf((double) valorInterpretado);
        } else if (valor.getTipo().getTipo() == tipoDato.BOOLEANO) {
            return String.valueOf((boolean) valorInterpretado);
        } else if (valor.getTipo().getTipo() == tipoDato.CARACTER) {
            if (valorInterpretado instanceof Character) {
                    return String.valueOf((char) valorInterpretado);
                } else if (valorInterpretado instanceof String && ((String) valorInterpretado).length() == 1) {
                    return valorInterpretado.toString();
                } else {
                    return new Errores("SEMANTICO", "Tipo de dato no soportado para toString(): " + valorInterpretado, this.linea, this.columna);
                }
        } else if (valor.getTipo().getTipo() == tipoDato.CADENA) {
            return valorInterpretado;
        } else {
            return new Errores("SEMANTICO", "Tipo de dato no soportado para toString(): " + valorInterpretado, this.linea, this.columna);
        }
    }
}
