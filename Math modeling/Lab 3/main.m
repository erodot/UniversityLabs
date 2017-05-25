c4 = 0.12;
m1 = 12;
m2 = 28;
m3 = 18;
h = 0.2;

beta = [0.1,0.1,0.4];

y = double(load('y10.txt'));
[row, col] = size(y);

y0 = zeros(row,1);
for i = 1:row
    y0(i) = y(i,1);
end

beta_prev = [0 0 0];

while abs(beta_prev-beta) > 0.001
    beta_prev = beta;
    
    % get A
    A = [0 1 0 0 0 0; 
       -(beta(2)+beta(1))/m1 0 beta(2)/m1 0 0 0;
       0 0 0 1 0 0;
       beta(2)/m2 0 -(beta(2)+beta(3))/m2 0 beta(3)/m2 0;
       0 0 0 0 0 1;
       0 0 beta(3)/m3 0 -(c4+beta(3))/m3 0];
    
    % solve dy/dt = Ay
    yNew = zeros(row, col);
    yNew(:,1) = y0';
    
    for i = 1:col-1
        k1 = h*(A*yNew(:,i));
        k2 = h*(A*(yNew(:,i)+0.5*k1));
        k3 = h*(A*(yNew(:,i)+0.5*k2));
        k4 = h*(A*(yNew(:,i)+k3));
                  
        yNew(:,i+1) = yNew(:,i) + (k1+2*k2+2*k3+k4)/6;
    end
     
    % get U
    U = RyngeKytta(h,A,beta(2),beta(3),m1,m2,yNew,row,col);
    
    % get delta-beta
    integral1 = zeros(3,3);
    integral2 = zeros(3,1);
    for i = 1:col
        integral1 = (integral1 + (U(:,:,i)'*U(:,:,i))*h);
        integral2 = (integral2 + h*U(:,:,i)'*(yNew(:,i)-y(:,i)));
    end
    
    beta = beta - (integral1\integral2)';
end

disp(beta);
   