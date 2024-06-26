package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;
import java.util.List;

public class AsignacionCombinada extends Instruccion {
    private String id;
    private Instruccion index1;
    private Instruccion index2;
    private Instruccion valor;
    private boolean esBidimensional;

    public AsignacionCombinada(String id, Instruccion index1, Instruccion valor, int linea, int columna) {
        super(null, linea, columna);
        this.id = id;
        this.index1 = index1;
        this.valor = valor;
        this.esBidimensional = false;
    }
    
    public AsignacionCombinada(String id, Instruccion index1, Instruccion index2, Instruccion valor, int linea, int columna) {
        super(null, linea, columna);
        this.id = id;
        this.index1 = index1;
        this.index2 = index2;
        this.valor = valor;
        this.esBidimensional = true;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "La variable: " + id + " no existe", this.linea, this.columna);
        }

        if (!simbolo.isMutable()) {
            return new Errores("SEMANTICO", "No se puede asignar un valor a la variable: " + id + " por ser Constante", this.linea, this.columna);
        }

        if (simbolo.getValor() instanceof List) {
            return asignarLista(arbol, tabla, simbolo);
        } else if (simbolo.getValor() instanceof Object[]) {
            return asignarVector(arbol, tabla, simbolo);
        } else {
            return new Errores("SEMANTICO", "La variable: " + id + " no es ni una lista ni un vector", this.linea, this.columna);
        }
    }
    
    private Object asignarLista(Arbol arbol, tablaSimbolos tabla, Simbolo simbolo) {
        List<Object> lista = (List<Object>) simbolo.getValor();
        Object indiceObject = index1.interpretar(arbol, tabla);

        if (!(indiceObject instanceof Integer)) {
            return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
        }

        int indice = (Integer) indiceObject;

        if (indice < 0 || indice >= lista.size()) {
            return new Errores("SEMANTICO", "Indice fuera de los limites de la lista", this.linea, this.columna);
        }

        Object valorInterpretado = valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        Tipo tipoValorInterpretado = obtenerTipoDesdeValor(valorInterpretado);
        if (tipoValorInterpretado == null || tipoValorInterpretado.getTipo() != simbolo.getTipo().getTipo()) {
            return new Errores("SEMANTICO", "Tipo erroneo de asignacion para la lista: " + id, this.linea, this.columna);
        }

        lista.set(indice, valorInterpretado);

        return null;
    }

    private Object asignarVector(Arbol arbol, tablaSimbolos tabla, Simbolo simbolo) {
        if (esBidimensional) {
            return asignarVectorBidimensional(arbol, tabla, simbolo);
        } else {
            return asignarVectorUnidimensional(arbol, tabla, simbolo);
        }
    }

    private Object asignarVectorUnidimensional(Arbol arbol, tablaSimbolos tabla, Simbolo simbolo) {
        Object[] vector = (Object[]) simbolo.getValor();
        Object indiceObject = index1.interpretar(arbol, tabla);

        if (!(indiceObject instanceof Integer)) {
            return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
        }

        int indice = (Integer) indiceObject;

        if (indice < 0 || indice >= vector.length) {
            return new Errores("SEMANTICO", "Indice fuera de los limites del vector: " + id, this.linea, this.columna);
        }

        Object valorInterpretado = valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        Tipo tipoValorInterpretado = obtenerTipoDesdeValor(valorInterpretado);
        if (tipoValorInterpretado == null || tipoValorInterpretado.getTipo() != simbolo.getTipo().getTipo()) {
            return new Errores("SEMANTICO", "Tipo erroneo de asignacion para el vector: " + id, this.linea, this.columna);
        }

        vector[indice] = valorInterpretado;

        return null;
    }

    private Object asignarVectorBidimensional(Arbol arbol, tablaSimbolos tabla, Simbolo simbolo) {
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

        Object valorInterpretado = valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        Tipo tipoValorInterpretado = obtenerTipoDesdeValor(valorInterpretado);
        if (tipoValorInterpretado == null || tipoValorInterpretado.getTipo() != simbolo.getTipo().getTipo()) {
            return new Errores("SEMANTICO", "Tipo erroneo de asignacion para el vector: " + id, this.linea, this.columna);
        }

        vector[indice1][indice2] = valorInterpretado;

        return null;
    }

    private Tipo obtenerTipoDesdeValor(Object valor) {
        if (valor instanceof Integer) {
            return new Tipo(tipoDato.ENTERO);
        } else if (valor instanceof Double) {
            return new Tipo(tipoDato.DECIMAL);
        } else if (valor instanceof String) {
            return new Tipo(tipoDato.CADENA);
        } else if (valor instanceof Character) {
            return new Tipo(tipoDato.CARACTER);
        } else if (valor instanceof Boolean) {
            return new Tipo(tipoDato.BOOLEANO);
        }
        return null;
    }
}
