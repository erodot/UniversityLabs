Program v11;
var D,M: integer;
begin
  read(D,M);
  if ((M=1)and((D>=21)and(D<=31)))or
     ((M=2)and((D>=1)and(D<=18))) then write('Водолій');
  if ((M=2)and((D>=19)and(D<=29)))or
     ((M=3)and((D>=1)and(D<=20))) then write('Риби');
  if ((M=3)and((D>=21)and(D<=31)))or
     ((M=4)and((D>=1)and(D<=20))) then write('Овен');
  if ((M=4)and((D>=21)and(D<=30)))or
     ((M=5)and((D>=1)and(D<=21))) then write('Телець');
  if ((M=5)and((D>=22)and(D<=31)))or
     ((M=6)and((D>=1)and(D<=21))) then write('Близнюки');
  if ((M=6)and((D>=22)and(D<=30)))or
     ((M=7)and((D>=1)and(D<=22))) then write('Рак');
  if ((M=7)and((D>=23)and(D<=31)))or
     ((M=8)and((D>=1)and(D<=23))) then write('Лев');
  if ((M=8)and((D>=24)and(D<=31)))or
     ((M=9)and((D>=1)and(D<=23))) then write('Діва');
  if ((M=9)and((D>=24)and(D<=30)))or
     ((M=10)and((D>=1)and(D<=23))) then write('Терези');
  if ((M=10)and((D>=24)and(D<=31)))or
     ((M=11)and((D>=1)and(D<=22))) then write('Скорпіон');
  if ((M=11)and((D>=23)and(D<=30)))or
     ((M=12)and((D>=1)and(D<=21))) then write('Стрілець');
  if ((M=12)and((D>=22)and(D<=31)))or
     ((M=1)and((D>=1)and(D<=20))) then write('Козеріг');
end.
