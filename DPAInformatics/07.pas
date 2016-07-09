Program v7;
Var n,i,answer:integer;
a:array[1..30] of integer;

begin
answer:=0;
readln(n);
for i:=1 to n do
begin
read(a[i]);
end;
for i:=1 to n do
if(a[i]>0) then answer:=answer+a[i]*a[i];
writeln(answer);
end.