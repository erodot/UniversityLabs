T=5;
t=0:0.01:T;

n=length(t);
y=dlmread('f10.txt',' ');

ff=abs(fft(y));
df=1/T;

figure('Color','w');
plot(t,ff)


fr=[];
for i=5:round(n/2)
    if ((ff(i-1)<ff(i)) & ff(i+1)<ff(i) )
        fr=[fr;(i-1)*df];
    end
end
fr

A=[];
for i=0:0.01:T
    tm=[i*i*i, i*i, i];
    for j=1:length(fr)
        tm=[tm,sin(2*pi*fr(j)*i)];
    end

    tm=[tm,1];
    A=[A;tm];
end

X=grav(A)*(y')

figure('Color','w');
plot(t,A*X)

figure('Color','w');
subplot(2,1,1), plot(t,A*X)
subplot(2,1,2), plot(t,y)
