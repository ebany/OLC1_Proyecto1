package sistemaanalitico;

import java_cup.runtime.*;
import java.io.Reader;
import java.util.Vector;
      
%%

%class AnalizadorLexico

%unicode
%line
%column 
%cup


%{

    /***************************************  Generamos un java_cup.symbol para guardar el tipo de token encontrado **********************************************/
    Vector erroresLex = new Vector(5,10);
    Vector erroresLexLinea = new Vector(5,10);
    Vector erroresLexColumna = new Vector(5,10);

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /*************************************** Generamos un symbol para el tipo de token encontrado junto con su valor *********************************************/

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

%}



/*********************************************************** Expresiones Regulares ***********************************************************/

Ascii		= 	[!-/:-@\\-`{-}]
Letra       =   [a-zA-ZñÑ]
numero 		=   [0-9]+
cadena 		= 	[\"][^\"\n]+[\"]						
id 			=   {Letra}({Letra}|{numero}|_)+			

%%

/**'************************************************************* TOKENS *********************************************************************/

<YYINITIAL> {

"("										{/*System.out.print("( 	");*/ return symbol(sym.tParentesisA,yytext()); }
")"										{/*System.out.print(") 	");*/ return symbol(sym.tParentesisC,yytext()); }
"%%"									{/*System.out.print("%%	");*/ return symbol(sym.tDoblePor,yytext()); }
";"                                     {/*System.out.print(";	");*/ return symbol(sym.tPuntoComa,yytext()); }
"."                                     {/*System.out.print(".	");*/ return symbol(sym.tConcatenacion,yytext()); }
"+"                                     {/*System.out.print("+	");*/ return symbol(sym.tUnoMas,yytext()); }
"*"                                     {/*System.out.print("*	");*/ return symbol(sym.tPor,yytext()); }
"?"                                     {/*System.out.print("?	");*/ return symbol(sym.tCeroUno,yytext()); }
"|"                                     {/*System.out.print("|	");*/ return symbol(sym.tOr,yytext()); }


"\"\\n\""                               {/*System.out.print("\\n 	");*/ return symbol(sym.tSaltoLinea,yytext()); }
"\"\\'\""                               {/*System.out.print("\\' 	");*/ return symbol(sym.tComillaS,yytext()); }
"\"\\t\""                               {/*System.out.print("\\t 	");*/ return symbol(sym.tTab,yytext()); }
"\"\\\"\""                              {/*System.out.print("\\\" 	");*/ return symbol(sym.tComillaD,yytext()); }
"[:todo:]"                              {/*System.out.print("[:todo:] 	");*/ return symbol(sym.tTodoV,yytext()); }

","                                     {/*System.out.print(",	");*/ return symbol(sym.tComa,yytext()); }
"~"                                     {/*System.out.print("~	");*/ return symbol(sym.tSeparadorC,yytext()); }
"->"                                    {/*System.out.print("->	");*/ return symbol(sym.tFlecha,yytext()); }
"CONJ"                                  {/*System.out.print("CONJ 	");*/ return symbol(sym.tConj,yytext()); }
"RESERV"                                {/*System.out.print("RESERV 	");*/ return symbol(sym.tReserv,yytext()); }
"retorno"                               {/*System.out.print("retorno 	");*/ return symbol(sym.tRetorno,yytext()); }
"yytext"                                {/*System.out.print("yytext 	");*/ return symbol(sym.tYyText,yytext()); }
"yyrow"                                 {/*System.out.print("yyrow 	");*/ return symbol(sym.tYyRow,yytext()); }
"yycol"                                 {/*System.out.print("yycol 	");*/ return symbol(sym.tYyCol,yytext()); }
"error"									{/*System.out.print("error 	");*/ return symbol(sym.terrorM,yytext()); }

"["										{/*System.out.print("[ 	");*/ return symbol(sym.tLlaveA,yytext()); }
"]"										{/*System.out.print("] 	");*/ return symbol(sym.tLlaveC,yytext()); }
":"                                     {/*System.out.print(":");*/ return symbol(sym.tDosPts,yytext()); }


[ \t\r\f] 				{ }

[\n]  					{/*System.out.print("\n");*/ }

{Ascii}					{System.out.print(yytext() + " "); return symbol(sym.tAscii,yytext()); }

{Letra}					{/*System.out.print(yytext() + " ");*/ return symbol(sym.tLetra,yytext()); }

{cadena}				{/*System.out.print(yytext() + " ");*/ return symbol(sym.tCadena,yytext()); }

{numero}				{/*System.out.print(yytext() + " ");*/ return symbol(sym.tNumero,yytext()); }

{id}					{/*System.out.print(yytext() + " ");*/ return symbol(sym.tId,yytext()); }


.                       { erroresLex.addElement(yytext()); erroresLexLinea.addElement(yyline+1); erroresLexColumna.addElement(yychar+1); }

}