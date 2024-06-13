package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.*;

public class Declaracion extends Instruccion {
    
    public String identificador;
    public Instruccion valor;
    
    public Declaracion(String identificador, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var valorInterpretado = this.valor.interpretar(arbol, tabla);
        
        if(valorInterpretado instanceof  Errores){
            return valorInterpretado;
        }
        
        if(this.valor.tipo.getTipo() != this.tipo.getTipo()){
            return  new Errores("SEMANTICO", "Tipo Erroneo", this.linea, this.columna);
        }
        
        Simbolo s = new Simbolo(this.tipo, this.identificador, valorInterpretado);
        
        boolean creacion = tabla.setVariable(s);
        
        if(!creacion){
            return  new Errores("SEMANTICO", "La Variable: " + creacion + " ya Existe", this.linea, this.columna);
        }
        
        return  null;
        
    }
    
}
