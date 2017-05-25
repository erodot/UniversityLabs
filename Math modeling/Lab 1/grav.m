function P=grav(X)

[m,n]=size(X);
P=X(1,1:n)'/(X(1,:)*X(1,:)');
a=X(1,1:n);
for i=2:m
    Z=eye(n)-P*a;
    e=X(i,:)*Z*X(i,:)';
    if e<1e-14
        R=P*P';
        e=1+X(i,:)*R*X(i,:)';
        P=[P-(R*X(i,:)'*X(i,:)*P)/e, R*X(i,:)'/e]; 
    else
        P=[P-(Z*X(i,:)'*X(i,:)*P)/e, Z*X(i,:)'/e];
    end
    a=[a;X(i,:)];
end