#### 📌 GRAMATICA

---

<p align="center">

|**CARNET**  |      **NOMBRE COMPLETO**          |  
|----------|:-----------------------------------:|
|202100096 |  RIVER ANDERSON - ISMALEJ ROMAN     |    
| AUXILIAR |            FABYAN REYNA             |   
| SECCION  |                "P"                  |  
</p>

---

    <INICIO> ::= <LISTAINSTRUCCIONES>
            | 

    <LISTAINSTRUCCIONES> ::= <LISTAINSTRUCCIONES> <INSTRUCCION>
                        | <INSTRUCCION>

    <INSTRUCCION> ::= <IMPRIMIR>
                    | <DECLARACION>
                    | <ASIGNACION>
                    | <IF>
                    | <FOR>
                    | <WHILE>
                    | <DOWHILE>
                    | <BREAK>
                    | <CONTINUE>
                    | <INCREMENTO>
                    | <DECREMENTO>
                    | error
                    | error T_SEMICOLON

    <IMPRIMIR> ::= T_PRINTLN P_LEFT <EXPRESION> P_RIGHT T_SEMICOLON

    <DECLARACION> ::= <MUTABILIDAD> T_ID T_COLON <TIPOSDECLARACION> T_EQUAL <EXPRESION> T_SEMICOLON
                    | <MUTABILIDAD> T_ID T_COLON <TIPOSDECLARACION> T_SEMICOLON

    <ASIGNACION> ::= T_ID T_EQUAL <EXPRESION> T_SEMICOLON

    <MUTABILIDAD> ::= T_VAR
                    | T_CONST

    <TIPOSDECLARACION> ::= T_INT
                        | T_DOUBLE
                        | T_STRING
                        | T_BOOL
                        | T_CHARACTER

    <INCREMENTO> ::= T_ID T_INCREMENT T_SEMICOLON
                | T_ID T_INCREMENT

    <DECREMENTO> ::= T_ID T_DECREMENT T_SEMICOLON
                | T_ID T_DECREMENT

    <IF> ::= T_IF P_LEFT <EXPRESION> P_RIGHT T_BRACKETIZ <LISTAINSTRUCCIONES> T_BRACKETDER <ELSE>

    <ELSE> ::= T_ELSE <IF>
            | T_ELSE T_BRACKETIZ <LISTAINSTRUCCIONES> T_BRACKETDER
            | 

    <FOR> ::= T_FOR P_LEFT <ASIGNACION> <EXPRESION> T_SEMICOLON <DECLARACIONFOR> P_RIGHT T_BRACKETIZ <LISTAINSTRUCCIONES> T_BRACKETDER

    <DECLARACIONFOR> ::= T_ID T_EQUAL <EXPRESION>
                    | <INCREMENTO>
                    | <DECREMENTO>

    <WHILE> ::= T_WHILE P_LEFT <EXPRESION> P_RIGHT T_BRACKETIZ <LISTAINSTRUCCIONES> T_BRACKETDER

    <DOWHILE> ::= T_DO T_BRACKETIZ <LISTAINSTRUCCIONES> T_BRACKETDER T_WHILE P_LEFT <EXPRESION> P_RIGHT T_SEMICOLON

    <BREAK> ::= T_BREAK T_SEMICOLON

    <CONTINUE> ::= T_CONTINUE T_SEMICOLON

    <EXPRESION> ::= T_MINUS <EXPRESION> %prec UMENOS
                | T_ID
                | P_LEFT <EXPRESION> P_RIGHT
                | <TIPOS>
                | <ARITMETICAS>
                | <RELACIONALES>
                | <LOGICOS>
                | <CASTEO>

    <ARITMETICAS> ::= <EXPRESION> T_PLUS <EXPRESION>
                    | <EXPRESION> T_MINUS <EXPRESION>
                    | <EXPRESION> T_TIMES <EXPRESION>
                    | <EXPRESION> T_DIVIDE <EXPRESION>
                    | <EXPRESION> T_POW <EXPRESION>
                    | <EXPRESION> T_MOD <EXPRESION>

    <RELACIONALES> ::= <EXPRESION> T_TWOEQUAL <EXPRESION> %prec T_TWOEQUAL
                    | <EXPRESION> T_NOTEQUAL <EXPRESION>
                    | <EXPRESION> T_LESSTHAN <EXPRESION>
                    | <EXPRESION> T_LESSTHANEQUAL <EXPRESION>
                    | <EXPRESION> T_GREATERTHAN <EXPRESION>
                    | <EXPRESION> T_GREATERTHANEQUAL <EXPRESION>

    <LOGICOS> ::= <EXPRESION> T_OR <EXPRESION>
                | <EXPRESION> T_AND <EXPRESION>
                | <EXPRESION> T_XOR <EXPRESION>
                | T_NOT <EXPRESION>

    <TIPOS> ::= T_ENTERO
            | T_CADENA
            | T_DECIMAL
            | T_CARACTER
            | T_BOLEANO

    <CASTEO> ::= P_LEFT <TIPOSDECLARACION> P_RIGHT <EXPRESION>

---