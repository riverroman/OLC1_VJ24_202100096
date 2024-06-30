package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.*;

public class AsignacionVar extends Instruccion {
    
    private String id;
    private Instruccion expresion;
    
    public AsignacionVar(String id, Instruccion expresion, int linea, int columna){
        super(new Tipo(tipoDato.VOID), linea, columna); // Inicializa this.tipo
        this.id = id;
        this.expresion = expresion;
    }
    
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoAsignacion = new NodoAst("Asignación");
        nodoAsignacion.agregarHijo("ID: " + id);
        nodoAsignacion.agregarHijo("=");
        nodoAsignacion.agregarHijoAST(expresion.astNodo());
        nodoAsignacion.agregarHijo(";");
        return nodoAsignacion;
    }
    
    @Override
    public Object interpretar(Arbol arbol,  tablaSimbolos tabla){
        //Si la variable existe
        var variable = tabla.getVariable(id);
        if(variable == null){
            return new Errores("SEMANTICO", "La variable: " + id +  " No Existe", this.linea, this.columna);
        }
        
        //Verificar si la variable es Mutable
        if(!variable.isMutable()){
            return new Errores("SEMANTICO", "No se puede asignar un valor a la variable: " + id + " por ser Constante", this.linea, this.columna);
        }
        
        // Interpretacion del nuevo valor a Asignar
        var newValor = this.expresion.interpretar(arbol, tabla);
        if (newValor instanceof  Errores){
            return  newValor;
        }
        
        // Validación de tipos
        if(this.expresion.tipo == null) {
            this.expresion.tipo = variable.getTipo();
        }
        
        if(variable.getTipo().getTipo() != this.expresion.tipo.getTipo()){
            return new Errores("SEMANTICO", "Tipo erroneo de asignacion para la variable: " + id, this.linea, this.columna);
        }
        
        this.tipo.setTipo(variable.getTipo().getTipo()); // Asegúrate de que this.tipo no es nulo
        variable.setValor(newValor);
        return  null;
    }
}
