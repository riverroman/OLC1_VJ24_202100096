package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import java.util.LinkedList;
import Simbolo.*;

public class Execute extends Instruccion{
    
    private String id;
    private LinkedList<Instruccion> parametros;
    
    public Execute(String id, LinkedList<Instruccion> parametros, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
        var busqueda = arbol.getFuncion(id);
        if(busqueda == null){
            return  new Errores("SEMANTICO", "La funcion: " + id + " no existe", this.linea, this.columna);
        }
        
        if(busqueda instanceof Metodo metodo){
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("EXECUTE");
            
            if(metodo.parametros.size() != this.parametros.size()){
                return new Errores("SEMANTICO", "Parametros Erroneos", this.linea, this.columna);
            }
            
            for(int i = 0; i < this.parametros.size(); i++){
                var identificador = (String) metodo.parametros.get(i).get("id");
                var valor = this.parametros.get(i);
                var tipo2 = (Tipo) metodo.parametros.get(i).get("tipo");
                
                var declaracionParametros = new Declaracion(identificador, valor, tipo2, this.linea, this.columna);
                
                var resultadoDeclaracion = declaracionParametros.interpretar(arbol, newTabla);
                
                if(resultadoDeclaracion instanceof Errores){
                    return resultadoDeclaracion;
                }
                
            }
            
            var resultadoFuncion = metodo.interpretar(arbol, newTabla);
            if(resultadoFuncion instanceof  Errores){
                return resultadoFuncion;
            }
        }
        return null ;
    }
}























