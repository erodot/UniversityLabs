Program v1;
Var n,k,num:integer;

function simple(n:integer):boolean;
Var i:integer;
begin
simple:=true;
for i:=2 to n-1 do
if (n mod i = 0) then 
simple:=false;
end;

begin
readln(n);
num:=2;
k:=0;
while(k<n) do
begin
if(simple(num)) then 
begin
write(num,' ');
k:=k+1;
end;
num:=num+1;
end;
end.