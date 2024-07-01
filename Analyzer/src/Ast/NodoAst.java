package Ast;

import java.util.ArrayList;
import java.util.List;

public class NodoAst {
    private String valor;
    private List<NodoAst> listaHijos;
    
    public NodoAst(String valor) {
        this.valor = valor;
        this.listaHijos = new ArrayList<>();
    }
    
    public void agregarHijo(String val) {
        listaHijos.add(new NodoAst(val));
    }

    public void agregarHijoAST(NodoAst hijo) {
        if (hijo != null) {
            listaHijos.add(hijo);
        }
    }
    
    public String getValor() {
        return valor;
    }
    
    public List<NodoAst> getListaHijos() {
        return listaHijos;
    }
}
