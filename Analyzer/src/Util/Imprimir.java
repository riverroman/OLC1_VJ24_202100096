package Util;

public class Imprimir {
    
    public static void Print(String expresion){

        if (expresion.startsWith("\"")) {
            expresion = expresion.substring(1, expresion.length());
        }

        if(expresion.endsWith("\"")){
            expresion = expresion.substring(0, expresion.length() - 1);
        }

        if(expresion.startsWith("\'")){
            expresion = expresion.substring(1, expresion.length());
        }

        if(expresion.endsWith("\'")){
            expresion = expresion.substring(0, expresion.length() - 1);
        }
        System.out.println(expresion);
    }
}
