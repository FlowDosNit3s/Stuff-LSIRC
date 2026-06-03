clc;
clear;

exec gauss.sce;
exec Fat_LU.sce;

A=[1 1 0 3;2 1 -1 1;3 -1 -1 2;-1 2 3 -1];

B=[4;1;-3;4];

[L,U]=gauss(A);

[X]=Fat_LU(L,U,B);

disp('U=',U,'L=',L,'X=',X);


