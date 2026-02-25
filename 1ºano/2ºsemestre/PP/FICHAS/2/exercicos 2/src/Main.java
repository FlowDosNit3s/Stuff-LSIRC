public class Main {
    public static void main(String[] args) {

        // ==========================================
        // EXERCÍCIO 1
        // ==========================================
        char [] frase ={ 'e', 'u',' ',
                's','o','u',' ',
                'a','l','u','n','o',' ',
                'd','a',' ',
                'E','S','T','G'};

        System.out.println("Resultado ex1: ");
        for(int i = 0; i < frase.length; i++){
            System.out.print(frase[i]);
        }

        // ==========================================
        // EXERCÍCIO 2
        // ==========================================
        System.out.println(" \n \nResultado ex2: ");

        int [][] matriz = {{11, 7, 333}, {-20, -23,63},{-22,501,10000}};
        int x = 0;

        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz.length; j++){
                System.out.print(matriz[i][j] + " ");
                x += matriz[i][j];
            }
        }
        System.out.println(" \nsoma: " +  x);
        System.out.println("media: " + (x/matriz.length));

        // ==========================================
        // EXERCÍCIO 3
        // ==========================================
        System.out.println(" ");

        int [] lista = {12,5,-21,10,-345,22,50,-125,80,-1};
        int z = 1;
        int contador = 0;
        int MaxNum = 0;

        for(int k = 0; k < lista.length; k++){
            if(lista [k] > 0){
                z *= lista[k];
            }
            if(lista[k] < 0){
                contador++;
            }
            if(lista[k] > MaxNum){
                MaxNum = lista[k];
            }
        }
        System.out.println("Resultado ex3: ");
        System.out.println("Produto dos valores positivos: " + z);
        System.out.println("Quantidade de valores negativos: " + contador);
        System.out.println("Maior valor: " + MaxNum);

        // ==========================================
        // EXERCÍCIO 4
        // ==========================================
        System.out.println(" ");

        char [] nome = {'A', 'n', 'a', ' ', 'S', 'a','n','t','o','s', '\n'};
        char [] vogais = {'a','e','i','o','u','A','E','I','O','U'};
        char [] consoantes = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z','B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z'};

        int contadorDeVogais = 0;
        int contadorDeConsoantes = 0;

        int posEspaco= -1;
        int posFim= -1;

        for(int l = 0 ; l < nome.length; l++){ //percorre o nome
            for(int m = 0; m < vogais.length; m++){ //verifica se o caracter é vogal
                if(nome[l] == vogais[m]){ //se for vogal
                    contadorDeVogais++; //incrementa o contador
                }
            }
            for(int n = 0; n < consoantes.length; n++){ //verifica se o caracter é consoante
                if(nome[l] == consoantes[n]){ //se for consoante
                    contadorDeConsoantes++; //incrementa o contador
                }
            }

            if(nome[l] == ' '){ //verifica a posição onde acaba o name.
                posEspaco = l;
            }else if(nome[l] == '\n'){ //verifica a posição que acaba o surname.
                posFim = l;
            }

        }

        System.out.println("Resultado ex4: ");
        System.out.print("Apelido, Nome: ");
        for(int g = posEspaco+1; g < posFim; g++){ //percorre o nome até a posição onde acaba o surname, começando na posição que acaba o name.
            System.out.print(nome[g]); //imprime o surname
        }
        System.out.print(", ");
        for(int f = 0; f < posEspaco; f++){ //percorre o nome até a posição onde acaba o name.
            System.out.print(nome[f]); //imprime o name
        }
        System.out.println(" ");
        System.out.println("Quantidade de vogais: " + contadorDeVogais);
        System.out.println("Quantidade de consoantes: " + contadorDeConsoantes);

        // ==========================================
        // EXERCÍCIO 5
        // ==========================================
        System.out.println(" \nResultado ex5: ");

        // Verifica se o utilizador passou exatamente dois parâmetros
        if (args.length == 2) {
            String nome2 = args[0];     // O primeiro parâmetro (índice 0)
            String apelido2 = args[1];  // O segundo parâmetro (índice 1)

            // Apresenta no formato: apelido2, nome
            System.out.println(apelido2 + ", " + nome2);
        } else {
            // Mensagem de erro caso o utilizador não passe os dois nomes
            System.out.println("Erro: Deve inserir exatamente o primeiro e ultimo nome.");

        }

        // ==========================================
        // EXERCÍCIO 6 (Alíneas A, B, C e D)
        // ==========================================
        System.out.println(" \nResultado ex6: ");

        int[] listaA = {2, -5, -121, 102, -35, -2, 0, -125, 802, -10};
        int[] listaB = {6, 99, -1, 12, 1, -2};

        // --- ALÍNEA A: Unir vetores ---
        int tamanhoTotal = listaA.length + listaB.length;
        int[] resultadoA = new int[tamanhoTotal];

        for(int i = 0; i < listaA.length; i++){
            resultadoA[i] = listaA[i];
        }
        for(int j = 0; j < listaB.length; j++){
            resultadoA[listaA.length + j] = listaB[j];
        }

        System.out.print("ALÍNEA A (União): [");
        for(int k = 0; k < resultadoA.length; k++){
            System.out.print(resultadoA[k]);
            if (k < resultadoA.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // --- ALÍNEA B: Contar repetidos no vetor unido ---
        int contadorDeRepetidos = 0;

        for(int t = 0; t < resultadoA.length; t++){
            boolean foiContado = false; // Tem de recomeçar a false a cada iteração!

            // Passo A: Verificar se já vimos este número (olhar para trás)
            for(int u = 0; u < t; u++){
                if(resultadoA[t] == resultadoA[u]){
                    foiContado = true;
                    break;
                }
            }

            // Passo B: Se ainda não o vimos, procurar repetições (olhar para a frente)
            if(!foiContado){
                for(int d = t + 1; d < resultadoA.length; d++){
                    if(resultadoA[t] == resultadoA[d]){
                        contadorDeRepetidos++;
                        break;
                    }
                }
            }
        }
        System.out.println("ALÍNEA B (Qtd de repetidos): " + contadorDeRepetidos);

        // --- ALÍNEA C: Elementos de listaA que não estão na listaB ---
        int tamanhoC = 0;
        for (int i = 0; i < listaA.length; i++) {
            boolean estaNaListaB = false;
            for (int j = 0; j < listaB.length; j++) {
                if (listaA[i] == listaB[j]) {
                    estaNaListaB = true;
                    break;
                }
            }
            if (!estaNaListaB) tamanhoC++;
        }

        int[] resultadoC = new int[tamanhoC];
        int indiceC = 0;
        for (int i = 0; i < listaA.length; i++) {
            boolean estaNaListaB = false;
            for (int j = 0; j < listaB.length; j++) {
                if (listaA[i] == listaB[j]) {
                    estaNaListaB = true;
                    break;
                }
            }
            if (!estaNaListaB) {
                resultadoC[indiceC] = listaA[i];
                indiceC++;
            }
        }

        System.out.print("ALÍNEA C (Em A mas não em B): [");
        for (int i = 0; i < resultadoC.length; i++) {
            System.out.print(resultadoC[i]);
            if (i < resultadoC.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // --- ALÍNEA D: Elementos comuns aos dois vetores (sem repetidos) ---
        int tamanhoD = 0;
        for (int i = 0; i < listaA.length; i++) {
            boolean jaVistoEmA = false;
            for (int k = 0; k < i; k++) {
                if (listaA[i] == listaA[k]) {
                    jaVistoEmA = true;
                    break;
                }
            }

            if (!jaVistoEmA) {
                for (int j = 0; j < listaB.length; j++) {
                    if (listaA[i] == listaB[j]) {
                        tamanhoD++;
                        break;
                    }
                }
            }
        }

        int[] resultadoD = new int[tamanhoD];
        int indiceD = 0;

        for (int i = 0; i < listaA.length; i++) {
            boolean jaVistoEmA = false;
            for (int k = 0; k < i; k++) {
                if (listaA[i] == listaA[k]) {
                    jaVistoEmA = true;
                    break;
                }
            }

            if (!jaVistoEmA) {
                for (int j = 0; j < listaB.length; j++) {
                    if (listaA[i] == listaB[j]) {
                        resultadoD[indiceD] = listaA[i];
                        indiceD++;
                        break;
                    }
                }
            }
        }

        System.out.print("ALÍNEA D (Comuns a A e B): [");
        for (int i = 0; i < resultadoD.length; i++) {
            System.out.print(resultadoD[i]);
            if (i < resultadoD.length - 1) System.out.print(", ");
        }
        System.out.println("]");

    }
}