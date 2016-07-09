Program v8;
Var n,i,av:integer;
a:array[1..20] of integer;

begin
av:=0;
for i:=1 to 20 do
begin
a[i]:=random(1001);
av:=av+a[i];
end;
for i:=1 to 20 do
write(a[i],' ');
writeln;
write(av/20);
end.