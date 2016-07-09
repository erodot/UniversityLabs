Program v4;
Var i,j:integer;
a:array[1..15,1..15] of integer;

function dec_to_hex(num:integer):string;
Var digit:integer;
s:string;
begin
dec_to_hex:='';
while(num>0) do begin
digit:=num mod 16;
if(digit<10) then
Insert(IntToStr(digit),s,1)
else 
Insert(chr(ord('A')+digit-10),s,1);
num:=num div 16;
end;
dec_to_hex:=s;
end;

begin
for i:=1 to 16 do
begin
for j:=1 to 16 do
write(dec_to_hex(i*j):4);
writeln;
end;
end.