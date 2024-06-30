package Expresiones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.*;

public class Aritmeticas extends Instruccion {
    
    private Instruccion operando1;
    private Instruccion operando2;
    private Operadores operacion;
    private Instruccion operandoUnico;
    
    public Aritmeticas(Instruccion operandoUnico, Operadores operacion, int linea, int columna){
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }
    
    public Aritmeticas(Instruccion operando1, Instruccion operando2, Operadores operacion, int linea, int columna){
        super(new Tipo(tipoDato.ENTERO),linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }
    
    @Override
    public NodoAst astNodo() {
        String nombreOperacion = operacion.toString();
        NodoAst nodoAritmetico = new NodoAst("Operación Aritmética: " + nombreOperacion);

        if (operandoUnico != null) {
            nodoAritmetico.agregarHijoAST(operandoUnico.astNodo());
        } else {
            nodoAritmetico.agregarHijoAST(operando1.astNodo());
            nodoAritmetico.agregarHijoAST(operando2.astNodo());
        }

        return nodoAritmetico;
    }

    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        Object opIzq = null, opDer  = null, Unico = null;
        if (this.operandoUnico != null){
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if(Unico instanceof Errores){
                return Unico;
            }
        }else{
            opIzq = this.operando1.interpretar(arbol, tabla);
            if(opIzq instanceof Errores){
                return opIzq;
            }
            opDer= this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores){
                return opDer;
            }
        }
       
        
        return switch(operacion){
            case SUMA ->
            {
                yield this.Suma(opIzq,opDer);
            }
            case RESTA -> 
            {
                yield  this.Resta(opIzq, opDer);
            }
            case MULTIPLICACION -> 
            {
                yield this.Multiplicacion(opIzq, opDer);
            }
            case DIVISION -> 
            {
                yield this.Division(opIzq, opDer);
            }
            case POTENCIA ->
            {
                yield this.Potencia(opIzq, opDer);
            }
            case MODULO ->
            {
                yield this.Modulo(opIzq, opDer);
            }
            case NEGACION ->
            {
                yield this.negacion(Unico);
            }
            default -> new Errores("SEMANTICO", "OPERADOR INVALIDO", this.columna, this.linea);
                
        };
    }
    
     //Suma
    public Object Suma(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case tipoDato.CARACTER ->{
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return (int) op1 + temp.charAt(0);
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Suma " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (double) op1 + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return (double) op1 + temp.charAt(0);
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Suma " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.BOOLEANO -> {
            switch (tipo2) {
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return Boolean.toString((boolean) op1) + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Suma " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        String temp = (String) op1;
                        return temp.charAt(0) + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        String temp = (String) op1;
                        return temp.charAt(0) + (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        return op1.toString() + op2.toString();
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Suma " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.CADENA -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return  op1.toString() + op2.toString();
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return op1.toString() + op2.toString();
                    }
                    case tipoDato.BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return op1.toString() + op2.toString();
                    }
                    case tipoDato.CARACTER -> { 
                        this.tipo.setTipo(tipoDato.CARACTER);
                        return op1.toString() + op2.toString();
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Suma " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Intento de Suma " + tipo1 + " y " + tipo2, this.linea, this.columna);
            }
        }        
    }
    
    //Resta
    public Object Resta(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 - (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return  (int) op1 - temp.charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Resta " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (double) op1 - (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return (double) op1 - temp.charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Resta " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        String temp = (String) op1;
                        return temp.charAt(0) - (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        String temp = (String) op1;
                        return temp.charAt(0) - (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Resta " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Intento de Resta " + tipo1 + " y " + tipo2, this.linea, this.columna);
            }
        }        
    }
    
    //Multiplicacion
    
    public Object Multiplicacion(Object op1, Object op2){
        
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 * (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 * (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return (int) op1 * temp.charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Multiplicacion " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (double) op1 * (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return (double) op1 * temp.charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Multiplicacion " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        String temp = (String) op1;
                        return temp.charAt(0) * (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        String temp = (String) op1;
                        return temp.charAt(0) * (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Multiplicacion " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Intento de Multiplicacion " + tipo1 + " y " + tipo2, this.linea, this.columna);
            }
        }        
    }
    
    //Division
    public Object Division(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 / (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 / (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return (int) op1  / (double) temp.charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Division " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (double) op1 / (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 / (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        String temp = (String) op2;
                        return (double) op1 / (double) temp.charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Division " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        String temp = (String) op1;
                        return (double) temp.charAt(0) / (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        String temp = (String) op1;
                        return (double) temp.charAt(0) / (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Division " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Intento de Division " + tipo1 + " y " + tipo2, this.linea, this.columna);
            }
        }        
    }
    
    //Potencia
    
    public Object Potencia(Object op1, Object op2) {
    var tipo1 = this.operando1.tipo.getTipo();
    var tipo2 = this.operando2.tipo.getTipo();

    switch (tipo1) {
        case tipoDato.ENTERO -> {
            switch (tipo2) {
                case tipoDato.ENTERO -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) Math.pow((int) op1, (int) op2);
                }
                case tipoDato.DECIMAL -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) Math.pow((int) op1, (double) op2);
                }
                default -> {
                    return new Errores("SEMANTICO", "Intento de Potencia " + tipo1 + " y " + tipo2, this.linea, this.columna);
                }
            }
        }
        case tipoDato.DECIMAL -> {
            switch (tipo2) {
                case tipoDato.ENTERO -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) Math.pow((double) op1, (int) op2);
                }
                case tipoDato.DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow((double) op1, (double) op2);
                }
                default -> {
                    return new Errores("SEMANTICO", "Intento de Potencia " + tipo1 + " y " + tipo2, this.linea, this.columna);
                }
            }
        }
            default -> {
                return new Errores("SEMANTICO", "Intento de Potencia " + tipo1 + " y " + tipo2, this.linea, this.columna);
            }
        }
    }
    
    
    //Modulo
    
    public Object Modulo(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO); 
                        return (int) op1 % (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 % (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Modulo " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO); 
                        return (double) op1 % (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 % (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Intento de Modulo " + tipo1 + " y " + tipo2, this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Intento de Modulo " + tipo1 + " y " + tipo2, this.linea, this.columna);
            }
        }        
    }
    
    
    
    //Negacion
    
    public Object negacion(Object op1){
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case tipoDato.ENTERO:
                this.tipo.setTipo(tipoDato.ENTERO);
                return (int) op1 * -1;
            case tipoDato.DECIMAL:
                this.tipo.setTipo(tipoDato.DECIMAL);
                return (double) op1 * -1;
            default:
                return new Errores("SEMANTICO", "Negacion Erronea", this.linea, this.columna);
        }
        
    }
   
}