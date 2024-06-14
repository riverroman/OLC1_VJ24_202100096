
package Expresiones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Simbolo.*;

public class AccesoVar extends Instruccion {
    
    private String id;
    
    public AccesoVar(String id, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var valor = tabla.getVariable(id);
        if (valor == null){
            return new Errores("SEMANTICO", "La Variable: " + id + " No Existe", this.linea, this.columna);
        }
        this.tipo.setTipo(valor.getTipo().getTipo());
        return valor.getValor();
    }
}
