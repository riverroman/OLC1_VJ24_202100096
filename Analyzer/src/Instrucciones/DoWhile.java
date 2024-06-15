
package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.*;
import java.util.LinkedList;

public class DoWhile extends Instruccion {
    private LinkedList<Instruccion> instrucciones;
    private Instruccion condicion;
    
    public DoWhile(LinkedList<Instruccion> instrucciones, Instruccion condicion, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.instrucciones = instrucciones;
        this.condicion = condicion;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var condicional = this.condicion.interpretar(arbol, tabla);
        if (condicional instanceof Errores){
            return condicional;
        }
        
        //Verificar la condicion de DO-While 
        if(this.condicion.getTipo().getTipo() != tipoDato.BOOLEANO){
            return  new Errores("SEMANTIO", "La expresion a evular no es Booleano", this.linea, this.columna);
        }
        
        do{
            var newTabla = new tablaSimbolos(tabla);
            
            for(var instruccion : this.instrucciones){
                var resultado = instruccion.interpretar(arbol, newTabla);
                
                if(resultado instanceof  Errores){
                    arbol.getErrores().add((Errores) resultado);
                }
                
                if(resultado instanceof Break){
                    return null;
                }
                
                if(resultado instanceof Continue){
                    break;
                }
                
            }
            
            condicional = this.condicion.interpretar(arbol, tabla);
            if(condicional instanceof  Errores){
                return  condicional;
            }
            
        }while((boolean) condicional);
        return  null;
    }
    
}
