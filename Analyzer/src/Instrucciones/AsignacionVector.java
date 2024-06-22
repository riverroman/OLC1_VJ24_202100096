package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;

public class AsignacionVector extends Instruccion {
    private String id;
    private Instruccion index;
    private Instruccion valor;
    
    public AsignacionVector(String id, Instruccion index, Instruccion valor, int linea, int columna) {
        super(null, linea, columna);
        this.id = id;
        this.index = index;
        this.valor = valor;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "La variable: " + id + " no existe", this.linea, this.columna);
        }
        
        if (!(simbolo.getValor() instanceof Object[])) {
            return new Errores("SEMANTICO", "La variable: " + id + " no es un vector", this.linea, this.columna);
        }
        
        Object[] vector = (Object[]) simbolo.getValor();
        
        Object indiceObject = index.interpretar(arbol, tabla);
        if (!(indiceObject instanceof Integer)) {
            return new Errores("SEMANTICO", "El indice debe ser entero", this.linea, this.columna);
        }
        
        int indice = (Integer) indiceObject;
        
        if (indice < 0 || indice >= vector.length) {
            return new Errores("SEMANTICO", "Indice fuera de los limites del vector", this.linea, this.columna);
        }
        
        Object valorInterpretado = valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }
        
        //Valida el valor a asignar
        Tipo tipoValorInterpretado = obtenerTipoDesdeValor(valorInterpretado);
        if (tipoValorInterpretado == null || tipoValorInterpretado.getTipo() != simbolo.getTipo().getTipo()) {
            return new Errores("SEMANTICO", "Tipo erroneo de asignacion para el vector: " + id, this.linea, this.columna);
        }
        
        vector[indice] = valorInterpretado;
        
        return null;
    }

    //Metoto para verificar el tipo de dato que se le pasa
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
