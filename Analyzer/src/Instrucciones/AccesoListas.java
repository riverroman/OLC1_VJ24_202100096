package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;
import java.util.List;

public class AccesoListas extends Instruccion {
    private String id;
    private Instruccion index;
    
    public AccesoListas(String id, Instruccion index, int linea, int columna) {
        super(null, linea, columna);
        this.id = id;
        this.index = index;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "La variable: " + id + " no existe", this.linea, this.columna);
        }
        
        if (!(simbolo.getValor() instanceof List)) {
            return new Errores("SEMANTICO", "La variable: " + id + " no es una lista", this.linea, this.columna);
        }
        
        List<Object> lista = (List<Object>) simbolo.getValor();
        
        Object indiceObject = index.interpretar(arbol, tabla);
        if (!(indiceObject instanceof Integer)) {
            return new Errores("SEMANTICO", "El índice debe ser entero", this.linea, this.columna);
        }
        
        int indice = (Integer) indiceObject;
        
        if (indice < 0 || indice >= lista.size()) {
            return new Errores("SEMANTICO", "Índice fuera de los límites de la lista", this.linea, this.columna);
        }
        
        return lista.get(indice);
    }
}
