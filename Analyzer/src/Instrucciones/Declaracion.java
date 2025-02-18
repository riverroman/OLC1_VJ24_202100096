package Instrucciones;

import Abstracto.Instruccion;
import Ast.NodoAst;
import Excepciones.Errores;
import Simbolo.Arbol;
import Simbolo.Simbolo;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;

public class Declaracion extends Instruccion {

    public String identificador;
    public Instruccion valor;
    public boolean mutabilidad;

    // Agregando la mutabilidad
    public Declaracion(String identificador, Instruccion valor, Tipo tipo, boolean mutabilidad, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valor = valor;
        this.mutabilidad = mutabilidad;
    }

    public Declaracion(String identificador, Instruccion valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valor = valor;
    }

    public Declaracion(String identificador, Tipo tipo, int linea, int columna){
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.valor = null;
    }
    
    @Override
    public NodoAst astNodo() {
        NodoAst nodoDeclaracion = new NodoAst("Declaracion");

        nodoDeclaracion.agregarHijo(mutabilidad ? "const" : "var");
        nodoDeclaracion.agregarHijo(identificador);
        nodoDeclaracion.agregarHijo(":");
        nodoDeclaracion.agregarHijo(tipo.toString());
        
        if (valor != null) {
            nodoDeclaracion.agregarHijo("=");
            nodoDeclaracion.agregarHijoAST(valor.astNodo());
        }
        
        nodoDeclaracion.agregarHijo(";");
        
        return nodoDeclaracion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Comprobar si `valor` es null antes de intentar interpretarlo
        Object valorInterpretado = (this.valor != null) ? this.valor.interpretar(arbol, tabla) : null;

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        // Validar el tipo
        if (this.valor != null && this.valor.tipo != null && this.valor.tipo.getTipo() != this.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipos erroneos", this.linea, this.columna);
        }

        Simbolo s = new Simbolo(this.tipo, this.identificador, valorInterpretado, this.mutabilidad);
        
        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente: " + identificador, this.linea, this.columna);
        }
        return null;
    }
}