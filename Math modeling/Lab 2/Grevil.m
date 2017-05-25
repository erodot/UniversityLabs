function P=Grevil(X)

[row,col]=size(X);
P=X(1,1:col)'/(X(1,:)*X(1,:)');
for i=2:row
    Z=eye(col)-P*X(1:i-1,:);
    e=X(i,:)*Z*X(i,:)';
    if e<0.001
        R=P*P';
        e=1+X(i,:)*R*X(i,:)';
        P=[P-(R*X(i,:)'*X(i,:)*P)/e, R*X(i,:)'/e]; 
    else
        P=[P-(Z*X(i,:)'*X(i,:)*P)/e, Z*X(i,:)'/e];
    end
end

disp('Grevil done');