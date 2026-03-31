public class Exercicio3 {

    public static void main(String[] args) {
        int[][] matriz = {
            {3, 5, 3},
            {7, 3, 2},
            {5, 7, 3},
            {1, 5, 7}
        };

        int linhas = 4;
        int colunas = 3;

        // Escrever a matriz
        System.out.println("Matriz:");
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        // Determinar o numero que mais vezes surge repetido
        int maxContagem = 0;
        int numMaisRepetido = matriz[0][0];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                int contagem = 0;
                for (int k = 0; k < linhas; k++) {
                    for (int l = 0; l < colunas; l++) {
                        if (matriz[i][j] == matriz[k][l]) {
                            contagem++;
                        }
                    }
                }
                if (contagem > maxContagem) {
                    maxContagem = contagem;
                    numMaisRepetido = matriz[i][j];
                }
            }
        }

        System.out.println("Numero mais repetido: " + numMaisRepetido + " (aparece " + maxContagem + " vezes)");
        System.out.println();

        // Maior e menor numero de cada linha
        for (int i = 0; i < linhas; i++) {
            int maior = matriz[i][0];
            int menor = matriz[i][0];
            for (int j = 1; j < colunas; j++) {
                if (matriz[i][j] > maior) {
                    maior = matriz[i][j];
                }
                if (matriz[i][j] < menor) {
                    menor = matriz[i][j];
                }
            }
            System.out.println("Linha " + i + ": Maior = " + maior + ", Menor = " + menor);
        }
    }
}
