package main;

import Abstracto.Instruccion;
import Analyzers.Parser;
import Analyzers.Lexer;
import Excepciones.Errores;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import Simbolo.Arbol;
import Simbolo.tablaSimbolos;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        String entrada = """
                         
                         var edad : double = 19.20;
                         println(edad);
                         edad++;
                         edad++;
                         edad++;
                         edad--;
                         println(edad + 10 / 2);
                         
                        """;
        
        analizar(entrada);
        
        //analizadores("src/Analyzers/", "Lexer.jflex", "Parser.cup");    
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
    
    public static void analizar(String entrada) {
        try {
            Lexer lexer = new Lexer(new BufferedReader(new StringReader(entrada)));
            Parser parser = new Parser(lexer);
            var resultado = parser.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            for (var a : ast.getInstrucciones()){
                var res = a.interpretar(ast, tabla);
                if (res instanceof Errores){
                    Errores error = (Errores) res;
                    System.out.println(error);
                }
            }
            System.out.println(ast.getConsola());
        } catch (Exception e) {
            System.out.println("Error fatal en compilacion de entrada.");
            System.out.println(e);
        }
    }
}
