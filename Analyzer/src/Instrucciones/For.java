package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import java.util.LinkedList;
import Simbolo.*;

public class For extends Instruccion {
    
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;
    
    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public NodoAst astNodo() {
        
        NodoAst nodoFor = new NodoAst("For");
        
        nodoFor.agregarHijo("for");
        nodoFor.agregarHijo("(");
        nodoFor.agregarHijoAST(this.asignacion.astNodo());
        nodoFor.agregarHijo(";");
        nodoFor.agregarHijoAST(this.condicion.astNodo());
        nodoFor.agregarHijo(";");
        nodoFor.agregarHijoAST(this.actualizacion.astNodo());
        nodoFor.agregarHijo(")");

        nodoFor.agregarHijo("{");
        NodoAst nodoInstrucciones = new NodoAst("Instrucciones-For");
        for (Instruccion instruccion : this.instrucciones) {
            nodoInstrucciones.agregarHijoAST(instruccion.astNodo());
        }
        nodoFor.agregarHijoAST(nodoInstrucciones);
        nodoFor.agregarHijo("}");

        return nodoFor;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        
        // Se crea un nuevo entorno
        tablaSimbolos newTabla = new tablaSimbolos(tabla);
        
        // Asignación -> Declaración
        Object res1 = this.asignacion.interpretar(arbol, newTabla);
        if (res1 instanceof Errores) {
            return res1;
        }
        
        // Validar la condición booleana
        Object condicional = this.condicion.interpretar(arbol, newTabla);
        if (condicional instanceof Errores) {
            return condicional;
        }
        
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "LA CONDICIONAL NO ES BOOLEANO", this.linea, this.columna);
        }
        
        // Acá empieza el Ciclo For -> O sea la implementación
        while ((boolean) this.condicion.interpretar(arbol, newTabla)) {
            
            // Se crea un nuevo Entorno
            tablaSimbolos newTabla2 = new tablaSimbolos(newTabla);
            
            for (Instruccion instruccion : this.instrucciones) {
                
                
                // Manejo de transferencia Break
                if (instruccion instanceof Break) {
                    return null;
                }
                // Manejo de transferencia Continue
                if (instruccion instanceof Continue) {
                    break;
                }
                
                // Return
                if(instruccion instanceof Expresiones.Nativo){
                    return instruccion;
                }
               
                Object resIns = instruccion.interpretar(arbol, newTabla2);
                
                // Manejo de transferencia Break
                if (resIns instanceof Break) {
                    return null;
                }
                // Manejo de transferencia Continue
                if (resIns instanceof Continue) {
                    break;
                }
                
                // Manejo de transferencia Return
                if(resIns instanceof  Expresiones.Nativo){
                    return  resIns;
                }
                
                if (resIns instanceof Errores) {
                    return resIns;
                }
            }
            
            // Actualización de la variable del ciclo
            Object actual = this.actualizacion.interpretar(arbol, newTabla);
            if (actual instanceof Errores) {
                return actual;
            }
            
            // Re-evaluar la condición con el nuevo entorno
            condicional = this.condicion.interpretar(arbol, newTabla);
            if (condicional instanceof Errores) {
                return condicional;
            }
        }
        return null;
    }    
}
