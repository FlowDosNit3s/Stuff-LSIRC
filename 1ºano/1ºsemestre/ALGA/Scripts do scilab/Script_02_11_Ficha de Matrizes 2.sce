//Ficha de Matrizes 2
//ex1
A=[1 0 1;2 3 1;-1 -3 2];
B=[2 3 -1;1 2 -3;-2 1 4]
//Transformar em (tr superior) iidentidade
//calcula a matriz identidade equivalente
rref(A)//I3 - logo c(A)=3
rref(B)//I3 - logo c(B)=3
//característica
rank(A)
rank(B)

//ex2
A=[1 -2 1;0,3,-1;1,1,0]
B=[4 1 1 5;2 -5 8 1;1 3 -1 2]
C=[0 1 3 -2;2 3 2 -1;2 1 -4 3]
D=[1 2 3;0 1 2;1 3 6;2 6 11;0 -1 -3]
rref(A) //a matriz ten 2 linhas não nulas=>c(A)=2
rank(A)

//ex 4 -inversa
A=[1 2;2 6]
inv(A)
//N-Matriz dos numeradores
//D-Matriz dos denominadores
[N,D]=rat(inv(A))

C=[1 2 -1;2 0 2;1 3 1]
C^-1
inv(C)
[N,D]=rat(inv(C))

D=[-2 3 -1;1 -3 1;-1 2 -1]
inv(D)
rref(D)

E=[2 3 1;-4 -2 -2;2 -5 1]
inv(E)
//inv: Problem is singular.
//ou seja não existe inversa de D
rref(E)
//porque a característica é 2<3(linhas da matriz)

//ex5
//X=AB^T
invA=[3 1 2;0 1 0;1 1 1]
B=[1 0 1;0 1 2]
//B'=B^T
X=inv(invA)*B'
