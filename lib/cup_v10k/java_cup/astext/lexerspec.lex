package java_cup.astext;
import java_cup.runtime.Symbol;

%%

%class AstLexer
%public
%cup

%{ 	
	private StringBuffer buffer=null;
	private String temp=null;
	
	private String white=new String("");
	private String bufWhite=null;
	
	private String comment=new String("");
	private String bufComment=null;
	
	private boolean firstEOF=true;
	
	private void outSym(String s) {

		if(bufWhite!=null) buffer.append(bufWhite);
		bufWhite=white;
		white=new String("");
				
		if(temp!=null) buffer.append(temp);
		temp=s;				
	}	
	
	public void setBuffer(StringBuffer buf) { buffer=buf; }
%}

LineTerminator = \n|\r|\r\n
Identificator = [a-zA-Z][a-zA-Z0-9_]*
WhitespaceCharacter = {LineTerminator} | [\ \t\v\f]


%state SINGLE_LINE_COMMENT
%state MULTILINE_COMMENT
%state CODE_STRING
	
%eofval{
	if(firstEOF==true) {
		if(bufWhite!=null) buffer.append(bufWhite);
		if(temp!=null) { buffer.append(temp); firstEOF=false; }
		if(white!=null) buffer.append(white);
		firstEOF=false;
	}
	return new Symbol(sym.EOF);
%eofval}
	
%%

<YYINITIAL> {

	{WhitespaceCharacter} { white+=yytext(); }
	
	/*keywords*/
	package 	{ outSym(yytext()); return new Symbol(sym.PACKAGE); }
	import 		{ outSym(yytext()); return new Symbol(sym.IMPORT); }
	code 		{ outSym(yytext()); return new Symbol(sym.CODE); }
	action  	{ outSym(yytext()); return new Symbol(sym.ACTION); }
	parser 		{ outSym(yytext()); return new Symbol(sym.PARSER); }
	terminal  	{ outSym(yytext()); return new Symbol(sym.TERMINAL);}
	nonterminal { outSym(yytext()); return new Symbol(sym.NONTERMINAL);}
	non	  		{ outSym(yytext()); return new Symbol(sym.NON); }
	init  		{ outSym(yytext()); return new Symbol(sym.INIT); }
	scan	  	{ outSym(yytext()); return new Symbol(sym.SCAN); }
	with 		{ outSym(yytext()); return new Symbol(sym.WITH); }
	start		{ outSym(yytext()); return new Symbol(sym.START); }
	precedence	{ outSym(yytext()); return new Symbol(sym.PRECEDENCE); }
	left		{ outSym(yytext()); return new Symbol(sym.LEFT); }
	right		{ outSym(yytext()); return new Symbol(sym.RIGHT); }
	nonassoc	{ outSym(yytext()); return new Symbol(sym.NONASSOC); }
	"%"prec		{ outSym(yytext()); return new Symbol(sym.PERCENT_PREC); }
	
	/*arithmetic and logic ops*/
	"["	  		{ outSym(yytext()); return new Symbol(sym.LBRACK);}
	"]"			{ outSym(yytext()); return new Symbol(sym.RBRACK); }
	";"			{ outSym(yytext()); return new Symbol(sym.SEMI); }
	","			{ outSym(""); return new Symbol(sym.COMMA); }
	"*"			{ outSym(yytext()); return new Symbol(sym.STAR); }
	"."			{ outSym(yytext()); return new Symbol(sym.DOT); }
	":"			{ outSym(yytext()); return new Symbol(sym.COLON); }
	"::="		{ outSym(yytext()); return new Symbol(sym.COLON_COLON_EQUALS); }
	"|"			{ outSym(yytext()); return new Symbol(sym.BAR); }	
	
	/*added for the ast extension */
	"("			{ outSym(yytext()); return new Symbol(sym.LPAREN);}
	")"			{ outSym(yytext()); return new Symbol(sym.RPAREN);}
	
	/*number constants, char constants and identifiers*/
	{Identificator} { outSym(yytext()); return new Symbol(sym.ID, yytext()); }
	"{:" 	{ outSym(yytext()); yybegin(CODE_STRING); }

	"//"	{ white+=yytext(); yybegin(SINGLE_LINE_COMMENT); }
	"/*"	{ white+=yytext(); yybegin(MULTILINE_COMMENT); }	
	
	.		{ outSym(yytext()); return new Symbol(sym.INVALID); }
}

<SINGLE_LINE_COMMENT> {
	{LineTerminator}	{ white+=yytext(); yybegin(YYINITIAL); }
	{WhitespaceCharacter} { white+=yytext(); }
	.		{ white+=yytext(); }
}

<MULTILINE_COMMENT> {
	{WhitespaceCharacter} { white+=yytext(); }
	"*/"	{ white+=yytext(); yybegin(YYINITIAL); }
	.		{ white+=yytext(); }
}

<CODE_STRING> {
	{WhitespaceCharacter} { white+=yytext(); }
	":}"	{ outSym(yytext()); yybegin(YYINITIAL); return new Symbol(sym.CODE_STRING); }
	.		{ outSym(yytext()); }
}
	
