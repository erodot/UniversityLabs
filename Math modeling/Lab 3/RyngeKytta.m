function U = RyngeKytta(h,A,c2,c3,m1,m2,y,row,col)
    Utmp = zeros(row,3);
    U = zeros(row,3,col);
     
    for i = 1:col
        dfdb = [0 0 0;
                -y(1,i)/m1 -y(1,i)/m1+y(3,i)/m1 0;
                0 0 0;
                0 y(1,i)/m2-y(3,i)/m2 -c2*y(1,i)/(m2*m2)+(c2+c3)*y(3,i)/(m2*m2)-c3*y(5,i)/(m2*m2);
                0 0 0;
                0 0 0];
        k1 = h*(A*Utmp + dfdb);
        k2 = h*(A*(Utmp+0.5*k1)+dfdb);
        k3 = h*(A*(Utmp+0.5*k2)+dfdb);
        k4 = h*(A*(Utmp+k3)+dfdb);
        
        Utmp = Utmp + (k1+2*k2+2*k3+k4)/6;

        U(:,:,i+1) = Utmp;
    end
end