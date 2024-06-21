package Simbolo;

public class Simbolo {
    
    private Tipo tipo;
    private String id;
    private Object valor;
    private boolean mutabilidad; // var true, const false

    // Constructor para cuando no hay valor inicial
    public Simbolo(Tipo tipo, String id, boolean mutabilidad) {
        this.tipo = tipo;
        this.id = id;
        this.mutabilidad = mutabilidad;
    }
    
    // Constructor completo
    public Simbolo(Tipo tipo, String id, Object valor, boolean mutabilidad) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.mutabilidad = mutabilidad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }

    public Object getValor() {
        return valor;
    }

    public boolean isMutable() {
        return mutabilidad;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    public void setMutability(boolean mutabilidad) {
        this.mutabilidad = mutabilidad;
    }
}
