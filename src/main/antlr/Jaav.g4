grammar Jaav;

@header{
package moe.lemonneko.jaav;
}

// RULES
compilationUnit : ( variable | print )* EOF;
variable : VAR ID VAR_DEF_INFIX equals value;
equals : EQUALS;
print : PRINT ( ID | value );
value : NUMBER | STRING;

VAR : '声明名为';
VAR_DEF_INFIX : '的变量';
EQUALS : '赋值为';
PRINT : '向控制台输出';
ID : [a-zA-Z][a-zA-Z0-9]*;
NUMBER : [1-9][0-9]*;
STRING : '"'.*?'"';
WS : [ \t\n\r]+ -> skip;