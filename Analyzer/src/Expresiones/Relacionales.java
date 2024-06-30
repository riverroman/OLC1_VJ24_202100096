package Expresiones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.*;

public class Relacionales extends Instruccion {
    
    private Instruccion condicional1;
    private Instruccion condicional2;
    private OperadoresRelacionales relacional;
    
    
    public Relacionales(Instruccion condicional1, Instruccion condicional2, OperadoresRelacionales relacional, int linea, int columna){
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.condicional1 = condicional1;
        this.condicional2 = condicional2;
        this.relacional = relacional;    
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoRelacional = new NodoAst("Operación Relacional: " + relacional);
        nodoRelacional.agregarHijoAST(condicional1.astNodo());
        nodoRelacional.agregarHijo(relacional.toString());
        nodoRelacional.agregarHijoAST(condicional2.astNodo());
        return nodoRelacional;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var condicionIzq = this.condicional1.interpretar(arbol, tabla);
        if(condicionIzq instanceof Errores){
            return condicionIzq;
        }
        
        var condicionDer = this.condicional2.interpretar(arbol, tabla);
        if(condicionDer instanceof  Errores){
            return condicionDer;
        }
        
        return switch (relacional) {
            case IGUALIGUAL ->
                this.equals(condicionIzq, condicionDer);
            case DIFERENTEIGUAL ->
                this.diferenteIgual(condicionIzq, condicionDer);
            case MENOR ->
                this.menor(condicionIzq, condicionDer);
            case MENORQUE -> 
                this.menorQue(condicionIzq, condicionDer);
            case MAYOR -> 
                this.mayor(condicionIzq, condicionDer);
            case MAYORQUE ->
                this.mayorQue(condicionIzq, condicionDer); 
            default ->
                new Errores("SEMANTICO", "Operador Relacional Invalido", this.linea, this.columna);
        };
    }
    
    //IGUAL IGUAL : ==
    
    public Object equals(Object compador1, Object comparador2){
        
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();
            
         return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) compador1 == (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) compador1 == (double) comparador2;
                    case tipoDato.CARACTER ->
                        (int) compador1 == (int) ((String) comparador2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.DECIMAL -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (double) compador1 == (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (double) compador1 == (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (double) compador1 == (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO -> 
                switch (comparando2) {
                    case tipoDato.BOOLEANO -> 
                        (boolean) compador1 == (boolean) comparador2;
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.CARACTER -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (int) ((String) compador1).charAt(0) == (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) ((String) compador1).charAt(0) == (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (int) ((String) compador1).charAt(0) == (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.CADENA -> 
                switch (comparando2) {
                    case tipoDato.CADENA -> 
                        ((String) compador1).equals((String) comparador2);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // DIFERENTE IGUAL : !=
    
    public Object diferenteIgual(Object compador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();
            
         return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) compador1 != (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) compador1 != (double) comparador2;
                    case tipoDato.CARACTER ->
                        (int) compador1 != (int) ((String) comparador2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.DECIMAL -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (double) compador1 != (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (double) compador1 != (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (double) compador1 != (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO -> 
                switch (comparando2) {
                    case tipoDato.BOOLEANO -> 
                        (boolean) compador1 != (boolean) comparador2;
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.CARACTER -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (int) ((String) compador1).charAt(0) != (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) ((String) compador1).charAt(0) != (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (int) ((String) compador1).charAt(0) != (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.CADENA -> 
                switch (comparando2) {
                    case tipoDato.CADENA -> 
                        !((String) compador1).equals((String) comparador2);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // MENOR : <
    
    public Object menor(Object compador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();
            
         return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) compador1 < (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) compador1 < (double) comparador2;
                    case tipoDato.CARACTER ->
                        (int) compador1 < (int) ((String) comparador2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.DECIMAL -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (double) compador1 < (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (double) compador1 < (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (double) compador1 < (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO ->
                 switch (comparando2){
                     case tipoDato.BOOLEANO -> 
                        ((boolean) compador1 ? 1 : 0) < ((boolean) comparador2 ? 1 : 0);
                     default ->
                     new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
            };
            case tipoDato.CARACTER -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (int) ((String) compador1).charAt(0) < (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) ((String) compador1).charAt(0) < (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (int) ((String) compador1).charAt(0) < (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // MENOR QUE : <=
    
    public Object menorQue(Object compador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();
            
         return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) compador1 <= (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) compador1 <= (double) comparador2;
                    case tipoDato.CARACTER ->
                        (int) compador1 <= (int) ((String) comparador2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.DECIMAL -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (double) compador1 <= (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (double) compador1 <= (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (double) compador1 <= (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO ->
                 switch (comparando2){
                     case tipoDato.BOOLEANO -> 
                        ((boolean) compador1 ? 1 : 0) <= ((boolean) comparador2 ? 1 : 0);
                     default ->
                     new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
            };
            case tipoDato.CARACTER -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (int) ((String) compador1).charAt(0) <= (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) ((String) compador1).charAt(0) <= (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (int) ((String) compador1).charAt(0) <= (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // MAYOR : >
    
    public Object mayor(Object compador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();
            
         return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) compador1 > (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) compador1 > (double) comparador2;
                    case tipoDato.CARACTER ->
                        (int) compador1 > (int) ((String) comparador2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.DECIMAL -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (double) compador1 > (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (double) compador1 > (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (double) compador1 > (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO ->
                 switch (comparando2){
                     case tipoDato.BOOLEANO -> 
                        ((boolean) compador1 ? 1 : 0) > ((boolean) comparador2 ? 1 : 0);
                     default ->
                     new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
            };
            case tipoDato.CARACTER -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (int) ((String) compador1).charAt(0) > (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) ((String) compador1).charAt(0) > (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (int) ((String) compador1).charAt(0) > (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
    
    // MAYOR QUE: >=
    
    public Object mayorQue(Object compador1, Object comparador2){
        var comparando1 = this.condicional1.tipo.getTipo();
        var comparando2 = this.condicional2.tipo.getTipo();
            
         return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) compador1 >= (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) compador1 >= (double) comparador2;
                    case tipoDato.CARACTER ->
                        (int) compador1 >= (int) ((String) comparador2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.DECIMAL -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (double) compador1 >= (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (double) compador1 >= (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (double) compador1 >= (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            case tipoDato.BOOLEANO ->
                 switch (comparando2){
                     case tipoDato.BOOLEANO -> 
                        ((boolean) compador1 ? 1 : 0) >= ((boolean) comparador2 ? 1 : 0);
                     default ->
                     new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
            };
            case tipoDato.CARACTER -> 
                switch (comparando2) {
                    case tipoDato.ENTERO -> 
                        (int) ((String) compador1).charAt(0) >= (int) comparador2;
                    case tipoDato.DECIMAL -> 
                        (int) ((String) compador1).charAt(0) >= (double) comparador2;
                    case tipoDato.CARACTER -> 
                        (int) ((String) compador1).charAt(0) >= (int) ((String) comparador2).charAt(0);
                    default -> 
                        new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido entre: " + comparando1 + " y " + comparando2, this.linea, this.columna);
        };
    }
}
   
    