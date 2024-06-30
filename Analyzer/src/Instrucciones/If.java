package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Expresiones.Nativo;
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

            if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
                return new Errores("SEMANTICO", "La expresión a evaluar no es de tipo Booleano", this.linea, this.columna);
            }

            var newTabla = new tablaSimbolos(tabla);
            if ((boolean) condicional) {
                for (var instruccion : this.instruccionesIf) {
                    if(instruccion instanceof Break || instruccion instanceof Continue){
                        return instruccion;
                    }
                    
                    var resultado = instruccion.interpretar(arbol, newTabla);

                    if(resultado instanceof Break || resultado instanceof Continue){
                        return resultado;
                    }

                    if (resultado instanceof Errores) {
                        arbol.getErrores().add((Errores) resultado);
                    }

                    if (resultado instanceof Expresiones.Nativo) {
                        return resultado;
                    }
                }
            } else if (this.instruccionElse != null) {
                var resultado = this.instruccionElse.interpretar(arbol, newTabla);
                //System.out.println(resultado);

                if (resultado instanceof Errores) {
                    arbol.getErrores().add((Errores) resultado);
                }

                if (resultado instanceof Expresiones.Nativo) {
                        return resultado;
                }
            }
            
            return null;
        }

}
