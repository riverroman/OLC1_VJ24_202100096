package Ast;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Grafica {

    private static int contador;
    private static StringBuilder cuerpo;

    public static String graficarArbol(NodoAst arbolitos) {
        contador = 1;
        cuerpo = new StringBuilder();
        graphAST("n0", arbolitos);
        String principal = String.format(
                "digraph arbolAST{ \n" +
                        "  fontname = \"Arial\"; \n" +
                        "  fontsize = 12; \n" +
                        "  node [shape=box, style=\"filled\", color=\"lightblue\", fillcolor=\"lightyellow\", fontcolor=\"black\"]; \n" +
                        "  edge [fontname=\"Arial\"]; \n" +
                        "  n0[label=\"%s\", shape=box, style=\"filled\", color=\"lightblue\", fillcolor=\"lightyellow\", fontcolor=\"black\"]; \n" +
                        "  %s \n" +
                        "}", escape(arbolitos.getValor()), cuerpo.toString());

        try {
            Files.write(Paths.get("arbolAST.dot"), principal.getBytes(StandardCharsets.UTF_8));
            Runtime.getRuntime().exec("dot -Tpng arbolAST.dot -o arbolAST.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return principal;
    }

    private static void graphAST(String texto, NodoAst padre) {
        for (NodoAst hijo : padre.getListaHijos()) {
            String nombreHijo = "n" + contador;
            cuerpo.append(String.format(
                    "%s[label=\"%s\", shape=box, style=\"filled\", color=\"lightblue\", fillcolor=\"lightyellow\", fontcolor=\"black\"]; \n" +
                            "%s -> %s;\n",
                    nombreHijo, escape(hijo.getValor()), texto, nombreHijo));
            contador++;
            graphAST(nombreHijo, hijo);
        }
    }

    private static String escape(String valor) {
        return valor.replace("\"", "\\\"");
    }
}
