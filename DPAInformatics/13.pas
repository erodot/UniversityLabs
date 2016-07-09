Program v13;
Var s:string;
left,right,i:integer;
ans:real;
num:array[1..11] of real;
symbol:array[1..10] of char;

procedure delete(left,right:integer);
Var i:integer;
begin
for i:=left to right-1 do
begin
num[i]:=num[i+1];
symbol[i-1]:=symbol[i];
end;
end;

begin
readln(s);
num[1]:=StrToInt(s[1]);
for i:=1 to length(s) div 2 do
begin
num[i+1]:=StrToInt(s[2*i+1]);
symbol[i]:=s[2*i];
end;
left:=1;
right:=length(s) div 2 + 1;
while(left<right) do
begin
if (symbol[left]='*') then
begin
num[left]:=num[left]*num[left+1];
delete(left+1,right);
right:=right-1;
end
else if (symbol[left]='/') then
begin
num[left]:=num[left]/num[left+1];
delete(left+1,right);
right:=right-1;
end
else left:=left+1;
end;
ans:=num[1];
for i:=2 to right do
if(symbol[i-1]='+') then ans:=ans+num[i]
else ans:=ans-num[i];
Writeln(ans);
end.
