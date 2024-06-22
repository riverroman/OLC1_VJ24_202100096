package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;
import java.util.List;

public class AsignacionListas extends Instruccion {
    
    private String id;
    private Instruccion index;
    private Instruccion valor;
    
    public AsignacionListas(String id, Instruccion index, Instruccion valor, int linea, int columna){
        super(null, linea, columna);
        this.id = id;
        this.index = index;
        this.valor = valor;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        
        Simbolo simbolo = tabla.getVariable(id);
        if(simbolo == null){
            return new Errores("SEMANTICO", "La variable: " + id + " no existe", this.linea, this.columna);
        }
        
        if(!(simbolo.getValor() instanceof List)){
            return new Errores("SEMANTICO", "La variable: " + id + " no es una lista", this.linea, this.columna);
        }
        
        List<Object> lista = (List<Object>) simbolo.getValor();
        
        Object indiceObject = index.interpretar(arbol, tabla);
        if(!(indiceObject instanceof Integer)){
            return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
        }
        
        int indice = (Integer) indiceObject;
        
        if(indice < 0 || indice >= lista.size()){
            return new Errores("SEMANTICO", "Indice fuera de la lista", this.linea, this.columna);
        }
        
        Object valorInterpretado = valor.interpretar(arbol, tabla);
        if(valorInterpretado instanceof Errores){
            return valorInterpretado;
        }
        
        if(simbolo.getTipo().getTipo() != this.valor.tipo.getTipo()){
             return new Errores("SEMANTICO", "Tipo erroneo de asignacion para la variable: " + id, this.linea, this.columna);
        }
        
        lista.set(indice, valorInterpretado);
        
        return null;
    }
}
