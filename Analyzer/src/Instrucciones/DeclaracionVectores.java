package Instrucciones;

import Abstracto.Instruccion;
import Simbolo.*;
import Excepciones.Errores;
import java.util.List;

public class DeclaracionVectores extends Instruccion {
    private String id;
    private Tipo tipo;
    private List<Instruccion> valores;
    private boolean mutabilidad;
    
    public DeclaracionVectores(String id, Tipo tipo, List<Instruccion> valores, boolean mutabilidad, int linea, int columna) {
        super(new Tipo(tipoDato.VECTOR), linea, columna);
        this.id = id;
        this.tipo = tipo;
        this.valores = valores;
        this.mutabilidad = mutabilidad;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object[] valoresInterpretados = new Object[valores.size()];
        
        for (int i = 0; i < valores.size(); i++) {
            Object valorInterpretado = valores.get(i).interpretar(arbol, tabla);
            if (valorInterpretado instanceof Errores) {
                return valorInterpretado;
            }

            //Error si el valor del vector es diferente al tipo declarado
            Tipo tipoValorInterpretado = obtenerTipoDesdeValor(valorInterpretado);
            if (tipoValorInterpretado == null || tipoValorInterpretado.getTipo() != this.tipo.getTipo()) {
                return new Errores("SEMANTICO", "Tipo erroneo en el valor: " + valorInterpretado + " para el vector: " + id, this.linea, this.columna);
            }

            valoresInterpretados[i] = valorInterpretado;
        }
        
        Simbolo simbolo = new Simbolo(tipo, id, valoresInterpretados, mutabilidad);
        
        boolean result = tabla.setVariable(simbolo);
        if (!result) {
            return new Errores("SEMANTICO", "La variable: " + id + " ya existe", this.linea, this.columna);
        }
               
        return null;
    }

    //Para obtener los datos mas seguro del tipo que se esta declarando
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
