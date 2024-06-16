
package Excepciones;

public class Errores {
    
    private String tipo;
    private String descripcion;
    private int linea;
    private int columna;
    
    public Errores(String tipo, String descripcion, int linea, int columna){
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return descripcion;
    }

    public void setDesc(String desc) {
        this.descripcion = desc;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString(){
        return "|Error|" + " |Tipo: " + tipo + " |Descripcion: " + descripcion + " |Linea: " + columna + " |Columna: " + linea ;
    } 
    
}
