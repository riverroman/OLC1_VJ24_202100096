package Instrucciones;

import  Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Expresiones.Nativo;
import java.util.LinkedList;
import Simbolo.*;

public class Else extends Instruccion {
    private LinkedList<Instruccion> instrucciones;
    
    public Else(LinkedList<Instruccion> instrucciones, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.instrucciones = instrucciones;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoElse = new NodoAst("Else");

        nodoElse.agregarHijo("else");
        nodoElse.agregarHijo("{");
        
        NodoAst nodoInstrucciones = new NodoAst("Instrucciones-Else");
        for (Instruccion instruccion : this.instrucciones) {
            nodoInstrucciones.agregarHijoAST(instruccion.astNodo());
        }
        nodoElse.agregarHijoAST(nodoInstrucciones);

        nodoElse.agregarHijo("}");

        return nodoElse;
    }

     
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var newTabla = new tablaSimbolos(tabla);
        for(var instruccion : this.instrucciones){
            var resultado = instruccion.interpretar(arbol, newTabla);
            
            /*  Manejo si existe un error */
            if(resultado instanceof  Errores){
                arbol.getErrores().add((Errores)resultado);
            }
            
            if (resultado instanceof Expresiones.Nativo) {
                    return  resultado;
            }
            
        }
        return null;
    }
    
}