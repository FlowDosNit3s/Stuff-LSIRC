public class Exercicio7 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Exercicio7 <dividendo> <divisor>");
            return;
        }

        int dividendo = Integer.parseInt(args[0]);
        int divisor = Integer.parseInt(args[1]);

        if (divisor == 0) {
            System.out.println("Erro: divisao por zero.");
            return;
        }

        // Determinar o sinal do resultado
        boolean negativo = (dividendo < 0) != (divisor < 0);

        // Trabalhar com valores absolutos
        int absDividendo = dividendo < 0 ? -dividendo : dividendo;
        int absDivisor = divisor < 0 ? -divisor : divisor;

        // Divisao por subtracao sucessiva
        int quociente = 0;
        int resto = absDividendo;
        while (resto >= absDivisor) {
            resto = resto - absDivisor;
            quociente++;
        }

        if (negativo) {
            quociente = -quociente;
        }

        System.out.println(dividendo + " / " + divisor + " = " + quociente);
        System.out.println("Resto: " + resto);
    }
}
