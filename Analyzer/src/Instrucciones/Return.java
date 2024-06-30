package Instrucciones;

import Abstracto.Instruccion;
import Excepciones.Errores;
import Expresiones.Nativo;
import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import Simbolo.tipoDato;

public class Return extends Instruccion {
    private Instruccion valorRetorno;
    
    public Return(Instruccion valorRetorno, int linea, int columna) {
        super(null, linea, columna);  // Aseguramos que el tipo sea VOID.
        this.valorRetorno = valorRetorno;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        
        if (valorRetorno != null) {
            Object valor = this.valorRetorno.interpretar(arbol, tabla);
            if (valor instanceof Errores) {
                return valor;
            }
            System.out.println("ENTROOO AQUI");
            //System.out.println(valor);
            
            return new Nativo(valor, new Tipo(tipoDato.DECIMAL),this.linea, this.columna);  // Return the interpreted value directly.
        }
        System.out.println("ENTRO AQUI 1");
        System.out.println(this.valorRetorno);
        return new Nativo("null", new Tipo(tipoDato.VOID), this.linea, this.columna);
         
    }
}

/*

START_WITH main();

void main(){
   const r : int = mcd(10);
   println(r);
}

int mcd(int a){
	return a + 1;
}

*/