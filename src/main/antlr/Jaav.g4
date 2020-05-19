grammar Jaav;

@header{
package moe.lemonneko.jaav;
}

// RULES
compilationUnit : ( variable | print )* EOF;
variable : VAR ID equals value;
equals : EQUALS;
print : PRINT ( ID | value );
value : NUMBER | STRING;

VAR : 'var';
EQUALS : '=';
PRINT : 'print';
ID : [a-zA-Z][a-zA-Z0-9]*;
NUMBER : [1-9][0-9]*;
STRING : '"'.*?'"';
WS : [ \t\n\r]+ -> skip;