package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import java.util.LinkedList;
import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import java.util.HashMap;

public class Metodo extends Instruccion{
        
    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;
    
    public Metodo(String id,LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int columna){
        super(tipo, linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        for(var i : this.instrucciones){
            var resultado = i.interpretar(arbol, tabla);
            if(resultado instanceof Errores){
                return resultado;
            }
        }
        return  null;
    }
}
