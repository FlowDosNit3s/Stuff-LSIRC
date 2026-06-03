//Resolução de sistemas de Cramer
//1)verificar se o sistema é de Cramer
A=[1 0 2;3 -1 1;1 3 0]
size(A)
//a matriz é quadrada
Delta=det(A)
//17!=0
//logo o sistema é de Cramer
//Logo possível e determinado
//Solução - Regra de Cramer
Ax=[4 0 2;10 -1 1;-7 3 0]
Deltax=det(Ax)
x=Deltax/Delta

Ay=[1 4 2;3 10 1;1 -7 0]
Deltay=det(Ay)
y=Deltay/Delta

Az=[1 0 4;3 -1 10;1 3 -7]
Deltaz=det(Az)
z=Deltaz/Delta

//Método da Inversão da Matriz dos Coeficientes
A=[1 0 2;3 -1 1;1 3 0]
B=[4;10;-7]
X=A^-1*B
X=inv(A)*B

//calcular a inversa
inv(A)
//numeros decimais
//racionalizar a matriz inversa
[Numeradores,Denominadores]=rat(inv(A)) 
