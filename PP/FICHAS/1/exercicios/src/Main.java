class Main {

    public static void main(String[] args) {

        //Exercicio 1

        /*
         * Imprimimos a duas variaveis char, ou seja, variaveis que armazenam um unico caractere (char l = 'l' , p = 'p';) :
         * -Resultado obtivemos os dois a serem imprimidos como l para o primeiro e p para o segundo.
         * De seguida, imprimimos mais duas variaveis int, ou seja, variaveis que armazenam números inteiros positivos ou negativos, sem casas decimais (int q = 4 , d = 2;) :
         * -Resultado obtivemos os dois a serem imprimidos como 4 para o primeiro e 2 para o segundo.
         * Desta vez imprimimos a soma das vazriaveis char (l e p) com o numero inteiro 2:
         * -Resultado obtivemos o valor 222.
         * -Este resultado é obtido devido ao não haver texto (String), o Java soma os valores númericos dos caracteres da tabela ASCII (l = 108 , p = 112, então 222 = 108 + 112 + 2).
         * Agora, imprimimos a soma das variaveis char (l e p) com o numero inteiro 2 contudo com "" no inicio do sout :
         * -Resultado obtivemos o valor lp2, devido ao começar com "", pois isso avisa o java que tudo deve ser tratado como texto.
         * Por fim, imprimimos a soma das variaveis int (q e d):
         * -Resultado obtivemos o valor 6.
         */

        char l = 'l' , p = 'p';
        int q = 4 , d = 2;
        System.out.println("Resultado exercico 1:");
        System.out.println(l);
        System.out.println(p);
        System.out.println(d);
        System.out.println(q);
        System.out.println(l + p + 2);
        System.out.println("" + l + p + 2);
        System.out.println(q + d);

        //Exercicio 2

        //array de interios (int)
        int [] numerosInt = {1,2,3,4,5,6,7,8,9,10};
        //array de deciamis (double)
        double [] numerosDouble = {1.1,2.2,3.3,4.4,5.5,6.6,7.7,8.8,9.9,10.10};
        boolean [] semaforo = {true,false,true,false,true,false,true,false,true,false};

        //testes
        System.out.println("Resultado exercico 2:");
        System.out.println(numerosInt[2]);
        System.out.println(numerosDouble[3]);
        System.out.println(semaforo[2]);

        System.out.println("Ultimo numeroInt: " + numerosInt[numerosInt.length-1]);
        System.out.println("Primeiro numeroDouble: " + numerosDouble[0]);

        //Exercicio 3

        /*
         * O output deste programa vai ser true.
         * Pois este passa a primeira verificação que dita que hisBalance >= myBalance, o que é verdade, entao canItakeHisMoney = true.
         * Na segunda verificação, verificar o "and" (&), ou seja tem de comprir os dois quesitos, e como canItakeHisMoney = true e hisBalance >= 3 (porque 13>=3), então podemos dizer que podemos pegar o dinheiro.
         */

        boolean canITakeHisMoney;
        int hisBalance = 5;
        long myBalance = 4;
        hisBalance += 8; //8+5 = 13
        canITakeHisMoney = hisBalance >= myBalance; //true
        canITakeHisMoney = canITakeHisMoney & (hisBalance >= 3); //true nos dois casos

        System.out.println("Resultado exercico 3:");
        System.out.println(canITakeHisMoney);

        //exercico 4

        int v = 0;
        v++; //v = 1
        int amount = v++; //v = 2 e amount = 1, amount fica 1 porque a variavel v ainda não foi incrementada, caso fosse ++v, amount ficaria 2

        System.out.println("Resultado exercico 4:");
        System.out.println(++v + " " + amount); //v = 3 porque v = v + 1, entao v = 2 + 1 = 3
        System.out.println(v); //v = 3

        //exercico 5

        System.out.println("Resultado exercico 5:");
        long x = 0;

        System.out.println("Valor do long: " + x);

        x= 3;

        System.out.println("Novo valor do long: " + x);

        boolean y = false;

        System.out.println("Valor do boolean: " + y);

        //exercicio 6

        double a;
        int b;

        System.out.println("Resultado exercico 6:");
        //System.out.println(a);
        //System.out.println(b);

        //o complilador apresenta erros porque as variaveis não foram declaradas;
        //java: variable a might not have been initialized

    }
}