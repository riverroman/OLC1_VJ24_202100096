package Expresiones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Simbolo.*;
import Excepciones.Errores;
import java.util.List;

public class AccesoCombinado extends Instruccion {
    private String id;
    private Instruccion index1;
    private Instruccion index2;
    private boolean esBidimensional;
    
    public AccesoCombinado(String id, Instruccion index1, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.index1 = index1;
        this.esBidimensional = false;
    }
    
    public AccesoCombinado(String id, Instruccion index1, Instruccion index2, int linea, int columna) {
        super(null, linea, columna);
        this.id = id;
        this.index1 = index1;
        this.index2 = index2;
        this.esBidimensional = true;
    }
    
     @Override
    public NodoAst astNodo() {
        NodoAst nodo = new NodoAst("Acceso Combinado");
        nodo.agregarHijo("ID: " + id);
        nodo.agregarHijo("[");
        nodo.agregarHijoAST(index1.astNodo());
        nodo.agregarHijo("]");
        if (esBidimensional) {
            nodo.agregarHijo("[");
            nodo.agregarHijoAST(index2.astNodo());
            nodo.agregarHijo("]");
        }
        return nodo;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "La variable: " + id + " no existe", this.linea, this.columna);
        }
        
        if (simbolo.getValor() instanceof Object[][]) {
            Object[][] vector = (Object[][]) simbolo.getValor();
            
            Object indiceObject1 = index1.interpretar(arbol, tabla);
            if (!(indiceObject1 instanceof Integer)) {
                return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
            }
            
            int indice1 = (Integer) indiceObject1;
            if (indice1 < 0 || indice1 >= vector.length) {
                return new Errores("SEMANTICO", "Indice fuera de los limites del vector", this.linea, this.columna);
            }

            if (esBidimensional) {
                Object indiceObject2 = index2.interpretar(arbol, tabla);
                if (!(indiceObject2 instanceof Integer)) {
                    return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
                }

                int indice2 = (Integer) indiceObject2;
                if (indice2 < 0 || indice2 >= vector[indice1].length) {
                    return new Errores("SEMANTICO", "Indice fuera de los limites del vector", this.linea, this.columna);
                }
                
                this.tipo = simbolo.getTipo();
                return vector[indice1][indice2];
            } else {
                this.tipo = new Tipo(tipoDato.VECTOR);
                return vector[indice1];
            }
        } else if (simbolo.getValor() instanceof Object[]) {
            if (esBidimensional) {
                return new Errores("SEMANTICO", "La variable: " + id + " no es un vector bidimensional", this.linea, this.columna);
            }
            
            Object[] vector = (Object[]) simbolo.getValor();
            Object indiceObject = index1.interpretar(arbol, tabla);
            if (!(indiceObject instanceof Integer)) {
                return new Errores("SEMANTICO", "El Indice debe ser entero", this.linea, this.columna);
            }
            
            int indice = (Integer) indiceObject;
            if (indice < 0 || indice >= vector.length) {
                return new Errores("SEMANTICO", "Indice fuera de los limites del vector", this.linea, this.columna);
            }
            
            this.tipo = simbolo.getTipo();
            return vector[indice];
        } else if (simbolo.getValor() instanceof List) {
            if (esBidimensional) {
                return new Errores("SEMANTICO", "La variable: " + id + " no es una lista", this.linea, this.columna);
            }
            
            List<Object> lista = (List<Object>) simbolo.getValor();
            Object indiceObject = index1.interpretar(arbol, tabla);
            if (!(indiceObject instanceof Integer)) {
                return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
            }
            
            int indice = (Integer) indiceObject;
            if (indice < 0 || indice >= lista.size()) {
                return new Errores("SEMANTICO", "Índice fuera de los limites de la lista", this.linea, this.columna);
            }
            
            this.tipo = simbolo.getTipo();
            return lista.get(indice);
        } else {
            return new Errores("SEMANTICO", "La variable: " + id + " no es un vector ni una lista", this.linea, this.columna);
        }
    }
}