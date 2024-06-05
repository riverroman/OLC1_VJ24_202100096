import java.io.StringReader;

import Analyzers.Parser;

public class App {
    public static void main(String[] args) throws Exception {
        //analizadores("src/Analyzers/", "Lexer.jflex", "Parser.cup");

        String entrada = """
                    println("Hola Mundo");
                    println(2+2);
                    println(2-3);
                """;

        analizar(entrada);

    }

    public static void analizadores(String ruta, String jflexFile, String cupFile){
        try {
            String opcionesJflex[] =  {ruta+jflexFile,"-d",ruta};
            jflex.Main.generate(opcionesJflex);

            String opcionesCup[] =  {"-destdir", ruta,"-parser","Parser",ruta+cupFile};
            java_cup.Main.main(opcionesCup);
            
        } catch (Exception e) {
            System.out.println("No se ha podido generar los analizadores");
            System.out.println(e);
        }
    }

    public static void analizar(String entrada){
         try {
            Analyzers.Lexer lexer = new Analyzers.Lexer(new StringReader(entrada)); 
            Analyzers.Parser parser = new Parser(lexer);
            parser.parse();
        } catch (Exception e) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println(e);
        } 
    }

}

