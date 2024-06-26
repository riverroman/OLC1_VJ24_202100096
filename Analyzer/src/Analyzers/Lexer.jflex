
package Analyzers;

import java_cup.runtime.*;
import java.util.LinkedList;
import Excepciones.Errores;

%%	

%{
    public LinkedList<Errores> ListaErrores = new LinkedList<>();
    
%}

%init{

    yyline = 1;
    yycolumn = 1;
    ListaErrores = new LinkedList<>();

%init}


%public 
%class Lexer
%cup
%char
%column
%line
%unicode
%ignorecase

entero = [0-9]+
decimal = [0-9]+\.[0-9]+
caracter = '[^']'
cadena = \"[^\"\n]*\"
id = [a-zA-z][a-zA-Z0-9_]*
boleano = true|false

%%

/*      Palabras Reservadas         */

"println"   { return new Symbol(sym.T_PRINTLN, yycolumn, yyline, yytext());  }
"var"       { return new Symbol(sym.T_VAR, yycolumn, yyline, yytext());      }
"const"     { return new Symbol(sym.T_CONST, yycolumn, yyline, yytext());    }
"int"       { return new Symbol(sym.T_INT, yycolumn, yyline, yytext());      }
"double"    { return new Symbol(sym.T_DOUBLE, yycolumn, yyline, yytext());   }
"string"    { return new Symbol(sym.T_STRING, yycolumn, yyline, yytext());   }
"bool"      { return new Symbol(sym.T_BOOL, yycolumn, yyline, yytext());     }
"char"      { return new Symbol(sym.T_CHARACTER, yycolumn, yyline, yytext());}
"void"      { return new Symbol(sym.T_VOID, yycolumn, yyline, yytext());     }


/*  Palabras Reservadas Sentencias de Control   */

"if"        { return new Symbol(sym.T_IF, yycolumn, yyline, yytext());}
"else"      { return new Symbol(sym.T_ELSE, yycolumn, yyline, yytext());}

/*  Palabras Reservadas Sentencias Ciclicas     */

"for"        { return new Symbol(sym.T_FOR, yycolumn, yyline, yytext());}

/*  Palabras Reservadas Sentencias De Transferencia   */

"break"     { return new Symbol(sym.T_BREAK, yycolumn, yyline, yytext());    }
"continue"  { return new Symbol(sym.T_CONTINUE, yycolumn, yyline, yytext()); }
"while"     { return new Symbol(sym.T_WHILE, yycolumn, yyline, yytext());    }
"do"        { return new Symbol(sym.T_DO, yycolumn, yyline, yytext());       }

/*  Palabras Reservadas para Funciones Nativas      */

"round"     { return new Symbol(sym.T_ROUND, yycolumn, yyline, yytext());  }
"length"    { return new Symbol(sym.T_LENGTH, yycolumn, yyline, yytext()); }

/*  Palabras Reservadas Sentencias List   */

"List"          { return new Symbol(sym.T_LIST, yycolumn, yyline, yytext()); }
"new"           { return new Symbol(sym.T_NEW, yycolumn, yyline, yytext()); }
"."             { return new Symbol(sym.T_DOT, yycolumn, yyline, yytext()); }
"append"        { return new Symbol(sym.T_APPEND, yycolumn, yyline, yytext());  }
"remove"        { return new Symbol(sym.T_REMOVE,  yycolumn, yyline, yytext());  }
"START_WITH"    { return new Symbol(sym.T_START_WITH, yycolumn, yyline, yytext()); }

/*      Signos      */

"("     { return new Symbol(sym.P_LEFT, yycolumn, yyline, yytext()); }
")"     { return new Symbol(sym.P_RIGHT, yycolumn, yyline, yytext()); }
";"     { return new Symbol(sym.T_SEMICOLON, yycolumn, yyline, yytext()); }
":"     { return new Symbol(sym.T_COLON, yycolumn, yyline, yytext()); }
"{"     { return new Symbol(sym.T_BRACKETIZ, yycolumn, yyline, yytext());}
"}"     { return new Symbol(sym.T_BRACKETDER, yycolumn, yyline, yytext());}
"["     { return new Symbol(sym.T_BRACEIZ, yycolumn, yyline, yytext());}
"]"     { return new Symbol(sym.T_BRACEDER, yycolumn, yyline, yytext());}
","     { return new Symbol(sym.T_COMMA, yycolumn, yyline, yytext());}
 
/*      Simbolos        */

"+"     { return new Symbol(sym.T_PLUS, yycolumn, yyline, yytext()); }
"-"     { return new Symbol(sym.T_MINUS, yycolumn, yyline, yytext()); }
"/"     { return new Symbol(sym.T_DIVIDE, yycolumn, yyline, yytext()); }
"**"    { return new Symbol(sym.T_POW, yycolumn, yyline, yytext()); }
"*"     { return new Symbol(sym.T_TIMES, yycolumn, yyline, yytext()); }
"%"     { return new Symbol(sym.T_MOD, yycolumn, yyline, yytext()); }

/*      Incremento y Decremento     */

"++"    { return new Symbol(sym.T_INCREMENT, yycolumn, yyline, yytext()); }
"--"    { return new Symbol(sym.T_DECREMENT, yycolumn, yyline, yytext()); }

/*      Relacionales       */

"="     { return new Symbol(sym.T_EQUAL, yycolumn, yyline, yytext());}
"=="    { return new Symbol(sym.T_TWOEQUAL, yycolumn, yyline, yytext()); }
"!="    { return new Symbol(sym.T_NOTEQUAL, yycolumn, yyline, yytext()); }
"<="    { return new Symbol(sym.T_LESSTHANEQUAL, yycolumn, yyline, yytext()); }
"<"     { return new Symbol(sym.T_LESSTHAN, yycolumn, yyline, yytext()); }
">="    { return new Symbol(sym.T_GREATERTHANEQUAL, yycolumn, yyline, yytext()); }
">"     { return new Symbol(sym.T_GREATERTHAN, yycolumn, yyline, yytext()); }

/*      Logicos         */

"||"    { return new Symbol(sym.T_OR,  yycolumn, yyline, yytext());  }
"&&"    { return new Symbol(sym.T_AND, yycolumn, yyline, yytext());  }
"^"     { return new Symbol(sym.T_XOR, yycolumn, yyline, yytext());  }  
"!"     { return new Symbol(sym.T_NOT, yycolumn, yyline, yytext());  } 

/*      Encapsulamiento     */

{entero}     { return new Symbol(sym.T_ENTERO, yycolumn, yyline, yytext()); }
{caracter}   { String contenido = yytext().substring(1, yytext().length() - 1); return new Symbol(sym.T_CARACTER, yycolumn, yyline, contenido);}
{decimal}    { return new Symbol(sym.T_DECIMAL, yycolumn, yyline, yytext()); }
{cadena}     { String contenido = yytext().substring(1, yytext().length() - 1); return new Symbol(sym.T_CADENA, yycolumn, yyline, contenido);}
{boleano}    { return new Symbol(sym.T_BOLEANO, yycolumn, yyline, yytext()); }
{id}         { return new Symbol(sym.T_ID, yycolumn, yyline, yytext()); }

/*      Comentarios         */

[ \t\r\n\f]                         {/* Se ignoran los espacios en blanco y comentarios de una sola linea */}
"//" [^\n]*                         {/* Se ignora comentario de una linea */}
"/*" [^*]* ("*" [^/]+)* "*/"        {/* Se ignora comentario multilineas */}

/*  Manejo de Errores Lexicos */

.  { ListaErrores.add(new Errores("LEXICO ", "Caracter Desconocido: " + yytext(), yycolumn, yyline ));}
