function [P] = MurPenroyz(X)
    delta = 1.0;
    [~,col] = size(X);
    E = eye(col,col);
    P = (X'*X+delta*E)\X';
    while 1
        delta = delta/2;
        P_next = (X'*X+delta*E)\X';     
        if norm(P_next-P) < 0.001
            break;
        end
        P = P_next;
    end

disp('MurPenroyz done');