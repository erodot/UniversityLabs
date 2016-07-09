Program v19;
var D: integer;
    M: string;
begin
  read(D,M);
  if ((M='січня')and((D>=21)and(D<=31)))or
     ((M='лютого')and((D>=1)and(D<=18))) then write('Водолій');
  if ((M='лютого')and((D>=19)and(D<=29)))or
     ((M='березня')and((D>=1)and(D<=20))) then write('Риби');
  if ((M='березня')and((D>=21)and(D<=31)))or
     ((M='квітня')and((D>=1)and(D<=20))) then write('Овен');
  if ((M='квітня')and((D>=21)and(D<=30)))or
     ((M='травня')and((D>=1)and(D<=21))) then write('Телець');
  if ((M='травня')and((D>=22)and(D<=31)))or
     ((M='червня')and((D>=1)and(D<=21))) then write('Близнюки');
  if ((M='червня')and((D>=22)and(D<=30)))or
     ((M='липня')and((D>=1)and(D<=22))) then write('Рак');
  if ((M='липня')and((D>=23)and(D<=31)))or
     ((M='серпня')and((D>=1)and(D<=23))) then write('Лев');
  if ((M='серпня')and((D>=24)and(D<=31)))or
     ((M='вересня')and((D>=1)and(D<=23))) then write('Діва');
  if ((M='вересня')and((D>=24)and(D<=30)))or
     ((M='жовтня')and((D>=1)and(D<=23))) then write('Терези');
  if ((M='жовтня')and((D>=24)and(D<=31)))or
     ((M='листопада')and((D>=1)and(D<=22))) then write('Скорпіон');
  if ((M='листопада')and((D>=23)and(D<=30)))or
     ((M='грудня')and((D>=1)and(D<=21))) then write('Стрілець');
  if ((M='грудня')and((D>=22)and(D<=31)))or
     ((M='січня')and((D>=1)and(D<=20))) then write('Козеріг');
end.
