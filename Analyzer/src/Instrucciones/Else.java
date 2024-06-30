package Instrucciones;

import  Abstracto.Instruccion;
import Excepciones.Errores;
import Expresiones.Nativo;
import java.util.LinkedList;
import Simbolo.*;

public class Else extends Instruccion {
    private LinkedList<Instruccion> instrucciones;
    
    public Else(LinkedList<Instruccion> instrucciones, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.instrucciones = instrucciones;
    }
     
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var newTabla = new tablaSimbolos(tabla);
        for(var instruccion : this.instrucciones){
            var resultado = instruccion.interpretar(arbol, newTabla);
            //System.out.println(resultado);
            
            /*  Manejo si existe un error */
            if(resultado instanceof  Errores){
                arbol.getErrores().add((Errores)resultado);
            }
            
            
            if (resultado instanceof Expresiones.Nativo) {
                    return  resultado;
            }
            
        }
        return null;
    }
    
}