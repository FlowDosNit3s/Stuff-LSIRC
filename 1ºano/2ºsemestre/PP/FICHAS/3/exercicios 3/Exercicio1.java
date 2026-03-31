public class Exercicio1 {

    public static void main(String[] args) {
        int N = 3;

        int[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] B = new int[N][N];

        // Calcular a transposta
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                B[i][j] = A[j][i];
            }
        }

        // Escrever a matriz original
        System.out.println("Matriz Original:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[i][j] + "\t");
            }
            System.out.println();
        }

        // Escrever a matriz transposta
        System.out.println("\nMatriz Transposta:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(B[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
