package Expresiones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tipoDato;
import Simbolo.tablaSimbolos;

public class Casteo extends Instruccion {

    private Instruccion valor;
    private Tipo tipoDestino;

    public Casteo(Instruccion valor, Tipo tipoDestino, int linea, int columna) {
        super(tipoDestino, linea, columna);
        this.valor = valor;
        this.tipoDestino = tipoDestino;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorInterpretado = valor.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        Tipo tipoValor = valor.getTipo();
        if (!esCasteoValido(tipoValor, tipoDestino)) {
            return new Errores("SEMANTICO", "No se puede castear desde " + tipoValor.getTipo() + " a " + tipoDestino.getTipo(), linea, columna);
        }

        switch (tipoValor.getTipo()) {
            case ENTERO:
                return casteoDesdeEntero(valorInterpretado);
            case DECIMAL:
                return casteoDesdeDecimal(valorInterpretado);
            case CADENA:
                return casteoDesdeCadena(valorInterpretado);
            case CARACTER:
                return casteoDesdeCaracter(valorInterpretado);
            default:
                return new Errores("SEMANTICO", "No se puede castear desde el tipo " + tipoValor.getTipo(), linea, columna);
        }
    }

    private boolean esCasteoValido(Tipo tipoValor, Tipo tipoDestino) {
        switch (tipoValor.getTipo()) {
            case ENTERO:
                return tipoDestino.getTipo() == tipoDato.DECIMAL || tipoDestino.getTipo() == tipoDato.CADENA || tipoDestino.getTipo() == tipoDato.CARACTER;
            case DECIMAL:
                return tipoDestino.getTipo() == tipoDato.ENTERO || tipoDestino.getTipo() == tipoDato.CADENA;
            case CADENA:
                return tipoDestino.getTipo() == tipoDato.ENTERO || tipoDestino.getTipo() == tipoDato.DECIMAL;
            case CARACTER:
                return tipoDestino.getTipo() == tipoDato.ENTERO || tipoDestino.getTipo() == tipoDato.DECIMAL || tipoDestino.getTipo() == tipoDato.CADENA;
            default:
                return false;
        }
    }

    private Object casteoDesdeEntero(Object valorInterpretado) {
        int valor = (int) valorInterpretado;
        switch (tipoDestino.getTipo()) {
            case DECIMAL:
                return (double) valor;
            case CADENA:
                return String.valueOf(valor);
            case CARACTER:
                return (char) valor;
            default:
                return new Errores("SEMANTICO", "No se puede castear entero a " + tipoDestino.getTipo(), linea, columna);
        }
    }

    private Object casteoDesdeDecimal(Object valorInterpretado) {
        double valor = (double) valorInterpretado;
        switch (tipoDestino.getTipo()) {
            case ENTERO:
                return (int) valor;
            case CADENA:
                return String.valueOf(valor);
            case CARACTER:
                return (char) valor;
            default:
                return new Errores("SEMANTICO", "No se puede castear decimal a " + tipoDestino.getTipo(), linea, columna);
        }
    }

    private Object casteoDesdeCadena(Object valorInterpretado) {
        String valor = (String) valorInterpretado;
        switch (tipoDestino.getTipo()) {
            case ENTERO:
                try {
                    return Integer.parseInt(valor);
                } catch (NumberFormatException e) {
                    return new Errores("SEMANTICO", "Formato incorrecto para entero en cadena: " + valor, linea, columna);
                }
            case DECIMAL:
                try {
                    return Double.parseDouble(valor);
                } catch (NumberFormatException e) {
                    return new Errores("SEMANTICO", "Formato incorrecto para decimal en cadena: " + valor, linea, columna);
                }
            default:
                return new Errores("SEMANTICO", "No se puede castear cadena a " + tipoDestino.getTipo(), linea, columna);
        }
    }

    private Object casteoDesdeCaracter(Object valorInterpretado) {
        if (valorInterpretado instanceof String && ((String) valorInterpretado).length() == 1) {
            char valor = ((String) valorInterpretado).charAt(0);
            switch (tipoDestino.getTipo()) {
                case ENTERO:
                    return (int) valor;
                case DECIMAL:
                    return (double) valor;
                case CADENA:
                    return String.valueOf(valor);
                default:
                    return new Errores("SEMANTICO", "No se puede castear caracter a " + tipoDestino.getTipo(), linea, columna);
            }
        } else {
            return new Errores("SEMANTICO", "No se puede castear el valor a caracter", linea, columna);
        }
    }

    @Override
    public Tipo getTipo() {
        return this.tipoDestino;
    }
}