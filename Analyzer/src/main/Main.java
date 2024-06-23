package main;

import Abstracto.Instruccion;
import Analyzers.Lexer;
import Analyzers.Parser;
import Excepciones.Errores;
import Simbolo.Arbol;
import Simbolo.tablaSimbolos;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        String entrada = """
                              
        var vector2D : int[][] = [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ];
        println(vector2D);
        vector2D[ 1 ][ 2 ] = 10;
        
        println(vector2D[ 1 ][ 2 ]); // Esto debería imprimir 10
        println(vector2D);
                                                                            
        """;
        
        analizar(entrada); 
        
        //analizadores("src/Analyzers/", "Lexer.jflex", "Parser.cup");    
        
        /*Interfaz Grafica*/
        
        //Principal p = new Principal();
        //p.setVisible(true);
        
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
    
    public static void analizar(String entrada){
        try {
            Lexer lexer = new Lexer(new BufferedReader(new StringReader(entrada)));
            Parser parser = new Parser(lexer);
            var resultado = parser.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            LinkedList<Errores> lista = new LinkedList<>();
            lista.addAll(lexer.ListaErrores);
            lista.addAll(parser.ListaErrores);
            for(var a : ast.getInstrucciones()){
                var res = a.interpretar(ast, tabla);
                if(res instanceof Errores){
                    Errores error = (Errores) res;
                    System.out.println(error);
                }    
            }
            for(Errores error : lista){
                System.out.println(error);
            }
   
            System.out.println(ast.getConsola());
        } catch (Exception e) {
            System.out.println("No se pudo analizar la entrada");
            System.out.println(e);
        }
    }
    
    
}