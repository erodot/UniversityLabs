Program v14;
Var n,k,i,j,imin,jmin,imax,jmax:integer;
a:array[1..30,1..30] of integer;

begin
readln(n);
imin:=1;
jmin:=1;
imax:=n;
jmax:=n;
k:=1;

while (imin<=imax) AND (jmin<=jmax) do
begin

for j:=jmin to jmax do
begin
a[imin,j]:=k;
k:=k+1;
end;

for i:=imin+1 to imax do
begin
a[i,jmax]:=k;
k:=k+1;
end;

for j:=jmax-1 downto jmin do
begin
a[imax,j]:=k;
k:=k+1;
end;

for i:=imax-1 downto imin+1 do
begin
a[i,jmin]:=k;
k:=k+1;
end;

imin:=imin+1;
jmin:=jmin+1;
imax:=imax-1;
jmax:=jmax-1;
end;

for i:=1 to n do
begin
for j:=1 to n do
write(a[i,j]:4);
writeln;
end;
end.