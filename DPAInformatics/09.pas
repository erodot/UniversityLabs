Program v9;
Var i:integer;
answer:real;
s:string;

begin
read(s);
answer:=StrToInt(s[1]);
for i:=1 to length(s) div 2 do
if(s[2*i]='*') then answer:=answer*StrToInt(s[2*i+1])
else answer:=answer/StrToInt(s[2*i+1]);
Writeln(answer);
end.