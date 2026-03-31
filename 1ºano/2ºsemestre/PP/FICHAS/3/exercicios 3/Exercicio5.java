public class Exercicio5 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Exercicio5 <valor> <e|d>");
            System.out.println("  e - converter de Euros para Dolares");
            System.out.println("  d - converter de Dolares para Euros");
            return;
        }

        double valor = Double.parseDouble(args[0]);
        String moeda = args[1];

        double taxaCambio = 1.09; // 1 EUR = 1.09 USD

        if (moeda.equals("e")) {
            double dolares = valor * taxaCambio;
            System.out.println(dolares + "$");
        } else if (moeda.equals("d")) {
            double euros = valor / taxaCambio;
            System.out.println(euros + " euros");
        } else {
            System.out.println("Moeda invalida. Use 'e' para Euros ou 'd' para Dolares.");
        }
    }
}
