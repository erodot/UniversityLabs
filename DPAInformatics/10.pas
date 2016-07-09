program v10; 

var a: array [1..90] of string; 
s:string; 
i,N:integer; 

function compare ( i,j:integer): boolean; 
var k:integer; 
w1,w2:string; 

begin 
w1:=a[i]; 
w2:=a[j]; 
k:=1; 
while (w1[k] >= w2[k]) do 
begin 
  if w1[k]>w2[k] then 
    begin 
      compare:=true; 
      break; 
    end 
  else k:=k+1; 
  if (Length(w1) = k-1) then 
  begin 
    compare:=false; 
    break; 
  end; 
  if (Length(w2) = k-1) then 
  begin 
    compare:=true; 
    break; 
  end; 
end; 

end; 

procedure zxc (); 
var j,i:integer; 
begin 
for j:=N-1 downto 1 do 
for i:=1 to j do 
begin 
if compare(i,i+1) then 
begin 
s:=a[i+1]; 
a[i+1]:=a[i]; 
a[i]:=s; 
end; 
end; 
for i:=1 to N do 
write (a[i],' '); 
end; 

begin 
readln(N); 
for i:=1 to N do 
readln 
(a[i]); 
for i:=1 to N do 
write(a[i],' '); 
writeln(); 
zxc(); 
end.
