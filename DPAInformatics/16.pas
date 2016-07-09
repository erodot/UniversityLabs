program v16;
var a: array[1..20,1..20] of integer;
var n,j,i:integer;
begin
readln(n);
for j:=1 to (n+1) div 2 do
begin
for i:=1 to n do
begin
a[2*j-1,i]:=(2*j-2)*n+i;
a[2*j,i]:=2*j*n-i+1;
end;
end;
for i:=1 to n do
begin
for j:=1 to n do
write(a[i,j]:4);
writeln;
end;
end.
