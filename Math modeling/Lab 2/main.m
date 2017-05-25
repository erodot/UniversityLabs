x = double(imread('x2.bmp'));
y = double(imread('y5.bmp'));

[row,col] = size(x);

for i=1:col
    x(row+1,i)=1;
end

[res1] = MurPenroyz(x);
imwrite(uint8(y*res1*x), 'res1.bmp', 'bmp');

[res2] = Grevil(x);
imwrite(uint8(y*res2*x), 'res2.bmp', 'bmp');
