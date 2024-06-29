package main;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        //analizadores("src/Analyzers/", "Lexer.jflex", "Parser.cup");   
        
        /*Interfaz Grafica*/
        // #includes();     #find -> Java
        
        Principal p = new Principal();
        p.setVisible(true);
        
    }
    
    public static void analizadores(String ruta, String jflexFile, String cupFile){
        try {
            String opcionesJflex[] = {ruta + jflexFile, "-d", ruta};
            jflex.Main.generate(opcionesJflex);
            String opcionesCup[] = {"-destdir", ruta, "-parser", "Parser", ruta + cupFile};
            java_cup.Main.main(opcionesCup);
        } catch (Exception e) {
            System.out.println("No se ha podido generar los analizadores");
            System.out.println(e);
        }
    }
}