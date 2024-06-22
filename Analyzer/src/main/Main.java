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

         var vector1 : string [] = [ "Hola", "Mundo" ];
         var vector2 : int [] = [ 2, 5, 3, 1, 4 ];
         const vector3 : double [] = [ (double)1, 2.0, 3.5, 4.5, 5.2 ];
         const vector4:int [][] = [ [ 1, 2 ], [ 3, 4 ] ];                
                                                                                  
         println(vector1);         
         println(vector2);         
         println(vector3);         
         println(vector4);         
                                                                                 
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