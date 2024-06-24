package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;
import java.util.List;


public class Remove extends Instruccion{
    
    private String id;
    private Instruccion index;
    
    public Remove(String id, Instruccion index, int linea, int columna){
        super(null, linea, columna);
        this.id = id;
        this.index = index;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        Simbolo simbolo = tabla.getVariable(id);
        if(simbolo == null){
            return  new Errores("SEMANTICO", "La variable: " + id + " no existe", this.linea, this.columna);
        }
        
        if(!(simbolo.getValor() instanceof List)){
            return  new Errores("SEMANTICO", "La variable: " + id + " no es una Lista", this.linea, this.columna);
        }
        
        List<Object> lista = (List<Object>) simbolo.getValor();
        
        Object indiceObjeto = index.interpretar(arbol, tabla);
        
        if(!(indiceObjeto instanceof Integer)){
            return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
        }
        
        int indice = (Integer) indiceObjeto;
        
        if(indice < 0 || indice >= lista.size()){
            return new Errores("SEMANTICO", "Indice fuera de la Lista", this.linea, this.columna);
        }
        
        this.tipo = simbolo.getTipo();
        return  lista.remove(indice);   
    }
}























