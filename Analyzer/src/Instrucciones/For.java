package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import java.util.LinkedList;
import Simbolo.*;


public class For extends Instruccion{
    
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;
    
    
    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        
        //Se crea un nuevo entorno
        var newTabla = new tablaSimbolos(tabla);
        
        //Asignacion -> Declaracion
        var res1 = this.asignacion.interpretar(arbol, newTabla);
        if(res1 instanceof Errores){
            return res1;
        }
        
        //Validar la condicion booleana
        var condicional = this.condicion.interpretar(arbol, newTabla);
        if(condicional instanceof Errores){
            return condicional;
        }
        
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO){
            return new Errores("SEMANTICO", "LA CONDICIONAL NO ES BOLENA", this.linea, this.columna);
        }
        
        //Aca empieza el Ciclo For -> Osea la implementacion
        while((boolean) this.condicion.interpretar(arbol, tabla)){
            
            //Se crea un nuevo Entorno
            var newTabla2 = new tablaSimbolos(newTabla);
            
            for (var i : this.instrucciones) {
                
                // Manejo de transferencia Break
                if (i instanceof Break) {
                    return null;
                }
                // Manejo de transferencia Continue
                if(i instanceof  Continue){
                    break;
                }
                
                var resIns = i.interpretar(arbol, newTabla2);
                
                //Manejo de transferencia Break
                if (resIns instanceof Break) {
                    return null;
                }
                //Manejo de transferencia Continue
                if(resIns instanceof Continue){
                    break;
                }
                
                
            }
            
            var actual = this.actualizacion.interpretar(arbol, newTabla);
            if (actual instanceof Errores){
                return actual;
            }
        }
        return  null;
    }
}
