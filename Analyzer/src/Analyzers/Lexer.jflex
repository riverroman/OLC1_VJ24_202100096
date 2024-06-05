
package Analyzers;

import java_cup.runtime.*;

%%	

%public 
%class Lexer
%cup
%char
%column
%line
%unicode
%ignorecase

%{ 
%} 

entero = [0-9]+
decimal = [0-9]+\.[0-9]+
caracter = '[^']'
cadena = \"[^\"\n]*\"
boleano = true|false

%%

/*      Palabras Reservadas         */

"println"   { return new Symbol(sym.T_PRINTLN, yycolumn, yyline, yytext()); }

/*      Signos      */

"("     { return new Symbol(sym.P_LEFT, yycolumn, yyline, yytext()); }
")"     { return new Symbol(sym.P_RIGHT, yycolumn, yyline, yytext()); }
";"     { return new Symbol(sym.T_SEMICOLON, yycolumn, yyline, yytext()); }


/*      Simbolos        */

"+"     { return new Symbol(sym.T_PLUS, yycolumn, yyline, yytext()); }
"-"     { return new Symbol(sym.T_MINUS, yycolumn, yyline, yytext()); }
"*"     { return new Symbol(sym.T_TIMES, yycolumn, yyline, yytext()); }
"/"     { return new Symbol(sym.T_DIVIDE, yycolumn, yyline, yytext()); }
"**"    { return new Symbol(sym.T_POW, yycolumn, yyline, yytext()); }
"%"     { return new Symbol(sym.T_MOD, yycolumn, yyline, yytext()); }

/*      Encapsulamiento     */

{entero}     { return new Symbol(sym.T_ENTERO, yycolumn, yyline, yytext()); }
{caracter}   { return new Symbol(sym.T_CARACTER, yycolumn, yyline, yytext()); }
{decimal}    { return new Symbol(sym.T_DECIMAL, yycolumn, yyline, yytext()); }
{cadena}     { return new Symbol(sym.T_CADENA, yycolumn, yyline, yytext()); }
{boleano}    { return new Symbol(sym.T_BOLEANO, yycolumn, yyline, yytext()); }

/*      Comentarios         */

[ \t\r\n\f]                         {/* Se ignoran los espacios en blanco y comentarios de una sola linea */}
"//" [^\n]*                         {/* Se ignora comentario de una linea */}
"/*" [^*]* ("*" [^/]+)* "*/"        {/* Se ignora comentario multilineas */}

/*  Manejo de Errores Lexicos */
.           	{ System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn); }
