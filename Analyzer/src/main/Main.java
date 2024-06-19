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
                         
        var numero : double = 18.45;
        var iteracion : int = 0;
        
        do {
            //println("Iteracion: " + iteracion);
            //println("Numero antes de redondear: " + numero);
            //println("Numero redondeado: " + round(numero));
            if(round(numero) == 19) {
                println("El numero es igual a 19");
            } else {
                println("Numero no iguales");
            }
        
            numero = numero + 0.1;
            iteracion = iteracion + 1;
        }while(iteracion < 5);
                
        println(""); 
        println(""); 
        println(""); 
        println(round(2.2));
        println(round(3.5));
        println(round(4.8));
                         
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
