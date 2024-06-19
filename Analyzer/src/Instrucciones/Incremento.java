package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.Arbol;
import Simbolo.Simbolo;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import Simbolo.tipoDato;

public class Incremento extends Instruccion {

    private String identificador;

    public Incremento(String identificador, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.identificador = identificador;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(this.identificador);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "Variable no existente: " + this.identificador, this.linea, this.columna);
        }

        if (!simbolo.isMutable()) {
            return new Errores("SEMANTICO", "No se puede incrementar una constante: " + this.identificador, this.linea, this.columna);
        }

        if (simbolo.getTipo().getTipo() != tipoDato.ENTERO && simbolo.getTipo().getTipo() != tipoDato.DECIMAL) {
            return new Errores("SEMANTICO", "Incremento no válido para el tipo de dato: " + simbolo.getTipo().getTipo(), this.linea, this.columna);
        }

        if (simbolo.getTipo().getTipo() == tipoDato.ENTERO) {
            int valor = (int) simbolo.getValor();
            simbolo.setValor(valor + 1);
        } else if (simbolo.getTipo().getTipo() == tipoDato.DECIMAL) {
            double valor = (double) simbolo.getValor();
            simbolo.setValor(valor + 1.0);
        }
        
        return null;
    }
}
