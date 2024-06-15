package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import java.util.LinkedList;
import Simbolo.*;

public class If extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private Instruccion instruccionElse;

    public If(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, Instruccion instruccionElse, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionElse = instruccionElse;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condicional = this.condicion.interpretar(arbol, tabla);

        if (condicional instanceof Errores) {
            return condicional;
        }

        // Verificar que la expresión o condicional a evaluar sea un tipo Booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La expresión a evaluar no es de tipo Booleano", this.linea, this.columna);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) condicional) {
            for (var instruccion : this.instruccionesIf) {
                
                // Manejo de Transferencia Break
                if(instruccion instanceof Break){
                    return instruccion;
                }
                
                if(instruccion instanceof Continue){
                    return  instruccion;
                }
                
                var resultado = instruccion.interpretar(arbol, newTabla);
                
                if(resultado instanceof Break){
                    return resultado;
                }
                
                if(resultado instanceof Continue){
                    return resultado;
                }
                
                /* Manejo de Errores */
                if (resultado instanceof Errores) {
                    arbol.getErrores().add((Errores) resultado);
                }
               
            }
        } else if (this.instruccionElse != null) {
            var resultado = this.instruccionElse.interpretar(arbol, newTabla);

            /* Manejo de Errores */
            if (resultado instanceof Errores) {
                arbol.getErrores().add((Errores) resultado);
            }
        }
        return null;
    }
}
