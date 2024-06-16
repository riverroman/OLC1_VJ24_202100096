package Instrucciones;

import Abstracto.Instruccion;
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
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Comprobar si `valor` es null antes de intentar interpretarlo
        Object valorInterpretado = (this.valor != null) ? this.valor.interpretar(arbol, tabla) : null;

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }
        
        //Validar el tipo
        if (this.valor != null && this.valor.tipo.getTipo() != this.tipo.getTipo()) {
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