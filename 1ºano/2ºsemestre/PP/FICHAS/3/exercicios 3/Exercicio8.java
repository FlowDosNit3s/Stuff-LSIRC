public class Exercicio8 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Exercicio8 <valor_em_euros>");
            return;
        }

        double valor = Double.parseDouble(args[0]);

        // Converter para centimos para evitar erros de virgula flutuante
        int centimos = (int) Math.round(valor * 100);

        int[] valoresMoedas = {200, 100, 50, 20, 10, 5, 2, 1};
        String[] nomesMoedas = {
            "2 euros", "1 euro", "50 centimos", "20 centimos",
            "10 centimos", "5 centimos", "2 centimos", "1 centimo"
        };

        System.out.println("Conversao de " + valor + " euros em moedas:");

        for (int i = 0; i < valoresMoedas.length; i++) {
            int quantidade = centimos / valoresMoedas[i];
            if (quantidade > 0) {
                centimos = centimos - (quantidade * valoresMoedas[i]);
                if (quantidade == 1) {
                    System.out.println(quantidade + " moeda de " + nomesMoedas[i]);
                } else {
                    System.out.println(quantidade + " moedas de " + nomesMoedas[i]);
                }
            }
        }
    }
}
