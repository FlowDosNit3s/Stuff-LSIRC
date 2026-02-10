class Main {

    public static void main(String[] args) {

        //Exercicio 1

        /**
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
        System.out.println(l);
        System.out.println(p);
        System.out.println(d);
        System.out.println(q);
        System.out.println(l + p + 2);
        System.out.println("" + l + p + 2);
        System.out.println(q + d);

        /* Output:
        *  l
        *  p
        *  2
        *  4
        *  222
        *  lp2
        *  6
        * */

        //Exercicio 2
    }
}