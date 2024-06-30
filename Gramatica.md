#### 📌 GRAMATICA FASE 2

---

<p align="center">

|**CARNET**  |      **NOMBRE COMPLETO**          |  
|----------|:-----------------------------------:|
|202100096 |  RIVER ANDERSON - ISMALEJ ROMAN     |    
| AUXILIAR |            FABYAN REYNA             |   
| SECCION  |                "P"                  |  
</p>

---
---

        <INICIO> ::= <LISTAINSTRUCCIONES>
            | 

        <LISTAINSTRUCCIONES> ::= <LISTAINSTRUCCIONES> <INSTRUCCION>
                                | <INSTRUCCION>

        <INSTRUCCION> ::= <IMPRIMIR>
                        | <DECLARACION>
                        | <DECLARACION_LISTA>
                        | <DECLARACION_VECTORES>
                        | <APPEND>
                        | <ASIGNACION_LISTAVECTORES>
                        | <ASIGNACION>
                        | <IF>
                        | <FOR>
                        | <WHILE>
                        | <DOWHILE>
                        | <BREAK>
                        | <CONTINUE>
                        | <RETURN>
                        | <INCREMENTO>
                        | <DECREMENTO>
                        | <METODO>
                        | <START_WITH>
                        | <LLAMADA>
                        | error
                        | error T_SEMICOLON

        <IMPRIMIR> ::= T_PRINTLN P_LEFT <EXPRESION> P_RIGHT T_SEMICOLON

        <DECLARACION> ::= <MUTABILIDAD> T_ID T_COLON <TIPOSDECLARACION> T_EQUAL <EXPRESION> T_SEMICOLON
                        | <MUTABILIDAD> T_ID T_COLON <TIPOSDECLARACION> T_SEMICOLON

        <DECLARACION_LISTA> ::= T_LIST T_LESSTHAN <TIPOSDECLARACION> T_GREATERTHAN T_ID T_EQUAL T_NEW T_LIST P_LEFT P_RIGHT T_SEMICOLON

        <DECLARACION_VECTORES> ::= <MUTABILIDAD> T_ID T_COLON <TIPOSDECLARACION> T_BRACEIZ T_BRACEDER T_EQUAL T_BRACEIZ LISTA_VALORES T_BRACEDER T_SEMICOLON
                                | <MUTABILIDAD> T_ID T_COLON <TIPOSDECLARACION> T_BRACEIZ T_BRACEDER T_BRACEIZ T_BRACEDER T_EQUAL T_BRACEIZ LISTA_VALORES_2D T_BRACEDER T_SEMICOLON

        <APPEND> ::= T_ID T_DOT T_APPEND P_LEFT <EXPRESION> P_RIGHT T_SEMICOLON

        <ASIGNACION_LISTAVECTORES> ::= T_ID T_BRACEIZ <EXPRESION> T_BRACEDER T_EQUAL <EXPRESION> T_SEMICOLON
                                | T_ID T_BRACEIZ <EXPRESION> T_BRACEDER T_BRACEIZ <EXPRESION> T_BRACEDER T_EQUAL <EXPRESION> T_SEMICOLON

        <LISTA_VALORES> ::= <LISTA_VALORES> T_COMMA <EXPRESION>
                        | <EXPRESION>

        <LISTA_VALORES_2D> ::= <LISTA_VALORES_2D> T_COMMA T_BRACEIZ <LISTA_VALORES> T_BRACEDER
                        | T_BRACEIZ <LISTA_VALORES> T_BRACEDER

        <ASIGNACION> ::= T_ID T_EQUAL <EXPRESION> T_SEMICOLON

        <MUTABILIDAD> ::= T_VAR
                        | T_CONST

        <TIPOSDECLARACION> ::= T_INT
                        | T_DOUBLE
                        | T_STRING
                        | T_BOOL
                        | T_CHARACTER
                        | T_VOID

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

        <RETURN> ::= T_RETURN T_SEMICOLON
                | T_RETURN <EXPRESION> T_SEMICOLON

        <EXPRESION> ::= T_MINUS <EXPRESION> %prec UMENOS
                | T_ID
                | P_LEFT <EXPRESION> P_RIGHT
                | <TIPOS>
                | <ARITMETICAS>
                | <RELACIONALES>
                | <LOGICOS>
                | <CASTEO>
                | <ROUND>
                | <ACCESO_COMBINADO>
                | <REMOVE>
                | <LENGTH>
                | <LLAMADA>
                | <TOSTRING>
                | <FIND>

        <ACCESO_COMBINADO> ::= T_ID T_BRACEIZ <EXPRESION> T_BRACEDER
                        | T_ID T_BRACEIZ <EXPRESION> T_BRACEDER T_BRACEIZ <EXPRESION> T_BRACEDER

        <REMOVE> ::= T_ID T_DOT T_REMOVE P_LEFT <EXPRESION> P_RIGHT

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

        <ROUND> ::= T_ROUND P_LEFT <EXPRESION> P_RIGHT

        <METODO> ::= <TIPOSDECLARACION> T_ID P_LEFT PARAMS P_RIGHT T_BRACKETIZ <LISTAINSTRUCCIONES> T_BRACKETDER
                | <TIPOSDECLARACION> T_ID P_LEFT P_RIGHT T_BRACKETIZ <LISTAINSTRUCCIONES> T_BRACKETDER

        <PARAMS> ::= <PARAMS> T_COMMA <TIPOSDECLARACION> T_ID
                | <TIPOSDECLARACION> T_ID

        <PARAMSCALL> ::= <PARAMSCALL> T_COMMA <EXPRESION>
                | <EXPRESION>

        <START_WITH> ::= T_START_WITH T_ID P_LEFT PARAMSCALL P_RIGHT T_SEMICOLON
                | T_START_WITH T_ID P_LEFT P_RIGHT T_SEMICOLON

        <LLAMADA> ::= T_ID P_LEFT PARAMSCALL P_RIGHT
                | T_ID P_LEFT P_RIGHT

        <LENGTH> ::= T_LENGTH P_LEFT <EXPRESION> P_RIGHT

        <RETURN> ::= T_RETURN T_SEMICOLON
                | T_RETURN <EXPRESION> T_SEMICOLON

        <TOSTRING> ::= T_TOSTRING P_LEFT <EXPRESION> P_RIGHT

        <FIND> ::= T_ID T_DOT T_FIND P_LEFT <EXPRESION> P_RIGHT


---
---