package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Expresiones.Nativo;
import java.util.LinkedList;
import Simbolo.*;


public class Llamada extends Instruccion {
    
    private String id;
    private LinkedList<Instruccion> parametros;
    
    public Llamada(String id, LinkedList<Instruccion> parametros, int linea, int columna){
        super(null, linea, columna);
        this.id = id;
        this.parametros = parametros;
    }
    
    @Override
    public  Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var busqueda = arbol.getFuncion(id);
        if(busqueda == null){
            return new Errores("SEMANTICO", "La funcion con id: " + id + " no existe", this.linea, this.columna);
        }
        
        if(busqueda instanceof Metodo metodo){
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("LLAMADA METODO" + this.id);
            if(metodo.parametros.size() != this.parametros.size()){
                return new Errores("SEMANTICO", "Parametros errores para la funcion: " + id, this.linea, this.columna);
            }
            
            for(int i = 0; i < this.parametros.size(); i++){
                var identificador = (String) metodo.parametros.get(i).get("id");
                
                var valor = this.parametros.get(i);
                
                var tipo2 = (Tipo) metodo.parametros.get(i).get("tipo");
                
                var declaracionParametro = new Declaracion(identificador, tipo2, this.linea, this.columna);
                
                var resultado = declaracionParametro.interpretar(arbol, newTabla);
                
                if(resultado instanceof Errores){
                    return resultado;
                }
                
                var valorInterpretado = valor.interpretar(arbol, tabla);
                if(valorInterpretado instanceof  Errores){
                    return  valorInterpretado;
                }
                
                var variable = newTabla.getVariable(identificador);
                if(variable == null){
                    return new Errores("SEMANTICO", "Error de declaracion en parametros", this.linea, this.columna);
                }
                
                if(variable.getTipo().getTipo() != valor.tipo.getTipo()){
                    return  new Errores("SEMANTICO", "Error en tipo parametro", this.linea, this.columna);
                }
                
                variable.setValor(valorInterpretado);
                
            }
            
            var resultadoFuncion = metodo.interpretar(arbol, newTabla);
            
            if(resultadoFuncion instanceof  Errores){
                return resultadoFuncion;
            }
            
            if(resultadoFuncion instanceof Expresiones.Nativo){
                return ((Nativo) resultadoFuncion).valor;
            }
            
        }
            return  null;
    }
}