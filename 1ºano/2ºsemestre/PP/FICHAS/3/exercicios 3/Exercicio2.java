public class Exercicio2 {

    public static boolean ePrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean ePerfeito(int n) {
        if (n < 2) return false;
        int soma = 1;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                soma += i;
            }
        }
        return soma == n;
    }

    public static void main(String[] args) {
        int[] vetor = {6, 7, 10, 28, 13, 496, 15, 23, 8128, 4, 17, 12};

        System.out.println("Vetor:");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Numeros primos:");
        for (int i = 0; i < vetor.length; i++) {
            if (ePrimo(vetor[i])) {
                System.out.print(vetor[i] + " ");
            }
        }
        System.out.println("\n");

        System.out.println("Numeros perfeitos:");
        for (int i = 0; i < vetor.length; i++) {
            if (ePerfeito(vetor[i])) {
                System.out.print(vetor[i] + " ");
            }
        }
        System.out.println();
    }
}
