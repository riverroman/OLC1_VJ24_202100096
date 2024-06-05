package Instrucciones;

public class Aritmeticas {

    public static String Suma(String a, String b){
        double left = Double.parseDouble(a);
        double right = Double.parseDouble(b);
        return String.valueOf(left + right);
    }

    public static String Resta(String a, String b){
        double left = Double.parseDouble(a);
        double right = Double.parseDouble(b);
        return String.valueOf(left - right);
    }
}