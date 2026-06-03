//Resolucao de sistemas por condensação
//matriz do sistema x y z t
A=[3 5 1 0;
1 1 2 1;
2 0 1 -1;
0 2 3 3;
3 1 3 0]
//Matriz dos termos independentes
B=[-1;
1;
4;
-2;
5]
//definir AB - matriz completa do sistema
M=[A B]
//classificação
rank(A)
rank(M)
//como rank(A)=rank(M)=c o sistema é possivel
size(A)
//ans  =5.   4.
//A segunda coordenada é o número de incónitas = n
//como n=4<c, o sistema é simplesmente indeterminado.
//consdensar - resolver
rref(M)
//racionalizar a matriz
//N=matriz dos numeradores
//D=Matriz dos denominadores
[N,D]=rat(rref(M))

