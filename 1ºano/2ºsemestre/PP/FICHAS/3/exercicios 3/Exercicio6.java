public class Exercicio6 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Exercicio6 <valor_em_centimos>");
            return;
        }

        int centimos = Integer.parseInt(args[0]);
        int euros = centimos / 100;
        int resto = centimos % 100;

        System.out.println(euros + " euros e " + resto + " centimos");
    }
}
