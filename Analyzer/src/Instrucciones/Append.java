
package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;
import java.util.List;


public class Append extends Instruccion {
    
    private String identificador;
    private Instruccion valor;
    
    public Append(String identificador, Instruccion valor, int linea, int columna){
        super(null, linea, columna);
        this.identificador = identificador;
        this.valor = valor;
    }
           
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        
        Simbolo simbolo = tabla.getVariable(this.identificador);
        
        if(simbolo == null){
           return new Errores("SEMANTICO", "La variable: " + identificador + " No existe", this.linea, this.columna);
        }
        
        if (!(simbolo.getValor() instanceof List)) {
            return new Errores("SEMANTICO", "La variable: " + this.identificador + " no es una lista.", this.linea, this.columna);
        }
        
        List<Object> lista = (List<Object>) simbolo.getValor();
        
        Object valorInterpretado = this.valor.interpretar(arbol, tabla);
        
        if(valorInterpretado instanceof Errores){
            return  valorInterpretado;
        }
        
        if(simbolo.getTipo().getTipo() != this.valor.tipo.getTipo()){
             return new Errores("SEMANTICO", "Tipo erroneo de asignacion para la variable: " + identificador, this.linea, this.columna);
        }
        
        lista.add(valorInterpretado);
        
        return  null;
    }
    
}
