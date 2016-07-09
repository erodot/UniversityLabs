Program v2;
Var a:array[1..30] of integer;
i,n:integer;

begin
readln(n);
a[1]:=1;
a[2]:=1;
for i:=3 to n do
a[i]:=a[i-1]+a[i-2];
for i:=1 to n do
write(a[i],' ');
end.