# CC_project
this project contains a lexical analyzer and a LL(1) parser for the following grammer


Develop Predictive Parser the following MINI C Language 
CProgram-->void main(){stmtSet } 
stmtSet-->stmt; |stmt;stemtSet
stmt--> decStmt | AS | CoutStmt | cinStmt 
decStmt--> Type idSet 
Type--> int 
idSet-->id |id,idSet
AS--> id=E 
E--> E+E | E*E |(E) |id | intLiteral 
coutStmt-->cout<<id 
cinstmt--> cin >> id 
