package Expresiones;

import Abstracto.Instruccion;
import Excepciones.Errores; 
import Simbolo.*;

public class Logicos extends Instruccion {
    
    private Instruccion condicional1;
    private Instruccion condicional2;
    private OperadoresLogicos logico;
    
    public Logicos(Instruccion condicional1, Instruccion condicional2, OperadoresLogicos logico, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.condicional1 = condicional1;
        this.condicional2 = condicional2;
        this.logico = logico;
    }

    public Logicos(Instruccion condicional1, OperadoresLogicos logico, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.condicional1 = condicional1;
        this.condicional2 = null;
        this.logico = logico;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var condicionIzq = this.condicional1.interpretar(arbol, tabla);
        if(condicionIzq instanceof Errores){
            return condicionIzq;
        }
        
        if (this.logico != OperadoresLogicos.NOT) {
            var condicionDer = this.condicional2.interpretar(arbol, tabla);
            if(condicionDer instanceof Errores){
                return condicionDer;
            }
            return switch (logico){
                case OR -> this.OperadorOr(condicionIzq, condicionDer);
                case AND -> this.OperadorAnd(condicionIzq, condicionDer);
                case XOR -> this.OperadorXor(condicionIzq, condicionDer);
                default -> new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.columna);
            };
        } else {
            return this.OperadorNot(condicionIzq);
        }
    }

    // OPERADOR LÓGICO OR
    public Object OperadorOr(Object comparador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();
    
        return switch (comparando1) {
            case tipoDato.ENTERO -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> (int) comparador1 != 0 || (int) comparador2 != 0;
                    case tipoDato.BOOLEANO -> (int) comparador1 != 0 || (boolean) comparador2;
                    default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO -> 
                switch (comparando2) {
                    case tipoDato.BOOLEANO -> (boolean) comparador1 || (boolean) comparador2;
                    case tipoDato.ENTERO -> (boolean) comparador1 || (int) comparador2 != 0;
                    default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // OPERADOR AND
    public Object OperadorAnd(Object comparador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();

        return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO -> (int) comparador1 != 0 && (int) comparador2 != 0;
                    case tipoDato.BOOLEANO -> (int) comparador1 != 0 && (boolean) comparador2;
                    default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO ->
                switch (comparando2) {
                    case tipoDato.BOOLEANO -> (boolean) comparador1 && (boolean) comparador2;
                    case tipoDato.ENTERO -> (boolean) comparador1 && (int) comparador2 != 0;
                    default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // OPERADOR XOR
    public Object OperadorXor(Object comparador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();

        return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO -> (int) comparador1 != 0 ^ (int) comparador2 != 0;
                    case tipoDato.BOOLEANO -> (int) comparador1 != 0 ^ (boolean) comparador2;
                    default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO ->
                switch (comparando2) {
                    case tipoDato.BOOLEANO -> (boolean) comparador1 ^ (boolean) comparador2;
                    case tipoDato.ENTERO -> (boolean) comparador1 ^ ((int) comparador2 != 0);
                    default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default -> new Errores("SEMANTICO", "Operador Logico Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // OPERADOR NOT
    public Object OperadorNot(Object comparador){
        if (comparador instanceof Boolean) {
            return !(Boolean) comparador;
        } else {
            return new Errores("SEMANTICO", "Operador Logico Invalido", this.linea, this.columna);
        }
    }
}