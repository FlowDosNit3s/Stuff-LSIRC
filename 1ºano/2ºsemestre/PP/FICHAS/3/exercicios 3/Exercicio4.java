public class Exercicio4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Exercicio4 <valor_em_graus>");
            return;
        }

        double graus = Double.parseDouble(args[0]);
        double radianos = graus * Math.PI / 180;

        System.out.println(graus + " graus = " + radianos + " radianos");
    }
}
