package Expresiones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;

public class AccesoVector extends Instruccion {
    private String id;
    private Instruccion index1;
    private Instruccion index2;
    private boolean esBidimensional;
    
    public AccesoVector(String id, Instruccion index1, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.index1 = index1;
        this.esBidimensional = false;
    }
    
    public AccesoVector(String id, Instruccion index1, Instruccion index2, int linea, int columna) {
        super(null, linea, columna);
        this.id = id;
        this.index1 = index1;
        this.index2 = index2;
        this.esBidimensional = true;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "La variable: " + id + " no existe", this.linea, this.columna);
        }
        
        if (esBidimensional) {
            if (!(simbolo.getValor() instanceof Object[][])) {
                return new Errores("SEMANTICO", "La variable: " + id + " no es un vector bidimensional", this.linea, this.columna);
            }
            
            Object[][] vector = (Object[][]) simbolo.getValor();
            Object indiceObject1 = index1.interpretar(arbol, tabla);
            Object indiceObject2 = index2.interpretar(arbol, tabla);
            
            if (!(indiceObject1 instanceof Integer) || !(indiceObject2 instanceof Integer)) {
                return new Errores("SEMANTICO", "Los indices deben ser enteros", this.linea, this.columna);
            }
            
            int indice1 = (Integer) indiceObject1;
            int indice2 = (Integer) indiceObject2;
            
            if (indice1 < 0 || indice1 >= vector.length || indice2 < 0 || indice2 >= vector[indice1].length) {
                return new Errores("SEMANTICO", "Indice fuera de los limites del vector", this.linea, this.columna);
            }
            
            this.tipo = simbolo.getTipo();  // Establece el tipo del valor
            return vector[indice1][indice2];
        } else {
            if (!(simbolo.getValor() instanceof Object[])) {
                return new Errores("SEMANTICO", "La variable: " + id + " no es un vector unidimensional", this.linea, this.columna);
            }
            
            Object[] vector = (Object[]) simbolo.getValor();
            Object indiceObject = index1.interpretar(arbol, tabla);
            
            if (!(indiceObject instanceof Integer)) {
                return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
            }
            
            int indice = (Integer) indiceObject;
            
            if (indice < 0 || indice >= vector.length) {
                return new Errores("SEMANTICO", "Indice fuera de los limites del vector", this.linea, this.columna);
            }
            
            this.tipo = simbolo.getTipo();
            return vector[indice];
        }
    }
}
