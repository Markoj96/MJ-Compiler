package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{
	//ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	//ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" "		{ }
"\b"	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f"	{ }

"program"	{ return new_symbol(sym.PROGRAM, yytext());}
"break"		{ return new_symbol(sym.BREAK, yytext());}
"class"		{ return new_symbol(sym.CLASS, yytext());}
"interface"	{ return new_symbol(sym.INTERFACE, yytext());}
"enum"		{ return new_symbol(sym.ENUM, yytext());}
"else"		{ return new_symbol(sym.ELSE, yytext());}
"const"		{ return new_symbol(sym.CONST, yytext());}
"if"		{ return new_symbol(sym.IF, yytext());}
"new"		{ return new_symbol(sym.NEW, yytext());}
"print"		{ return new_symbol(sym.PRINT, yytext());}
"read"		{ return new_symbol(sym.READ, yytext());}
"return"	{ return new_symbol(sym.RETURN, yytext());}
"void"		{ return new_symbol(sym.VOID, yytext());}
"for"		{ return new_symbol(sym.FOR, yytext());}
"extends"	{ return new_symbol(sym.EXTENDS, yytext());}
"continue"	{ return new_symbol(sym.CONTINUE, yytext());}
"+" 		{ return new_symbol(sym.ADD, yytext());}
"-" 		{ return new_symbol(sym.SUB, yytext());}
"*" 		{ return new_symbol(sym.MUL, yytext());}
"/" 		{ return new_symbol(sym.DIV, yytext());}
"%" 		{ return new_symbol(sym.MOD, yytext());}
"==" 		{ return new_symbol(sym.EQUAL, yytext());}
"!=" 		{ return new_symbol(sym.NOT_EQUAL, yytext());}
">" 		{ return new_symbol(sym.GREATER, yytext());}
">=" 		{ return new_symbol(sym.GREATER_OR_EQUAL, yytext());}
"<" 		{ return new_symbol(sym.LOWER, yytext());}
"<=" 		{ return new_symbol(sym.LOWER_OR_EQUAL, yytext());}
"&&" 		{ return new_symbol(sym.AND, yytext());}
"||" 		{ return new_symbol(sym.OR, yytext());}
"=" 		{ return new_symbol(sym.EQUAL, yytext());}
"++" 		{ return new_symbol(sym.INCREMENT, yytext());}
"--" 		{ return new_symbol(sym.DECREMENT, yytext());}
","			{ return new_symbol(sym.COMMA, yytext());}
";"			{ return new_symbol(sym.SEMI_COLON, yytext());}
"." 		{ return new_symbol(sym.DOT, yytext());}
"("			{ return new_symbol(sym.LEFT_PAREN, yytext());}
")"			{ return new_symbol(sym.RIGHT_PAREN, yytext());}
"[" 		{ return new_symbol(sym.LEFT_BRACKET, yytext());}
"]" 		{ return new_symbol(sym.RIGHT_BRACKET, yytext());}
"{"			{ return new_symbol(sym.LEFT_BRACE, yytext());}
"}"			{ return new_symbol(sym.RIGHT_BRACE, yytext());}

"//"		{yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

[0-9]+ 							{ return new_symbol(sym.numConst, new Integer (yytext())); }
("true"|"false")				{ return new_symbol(sym.boolConst, new Boolean (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }
'.' 							{ return new_symbol(sym.charConst, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }

