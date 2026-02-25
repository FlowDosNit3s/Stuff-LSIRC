package resources;

import monitor.SecurityMonitor;

/**
 * Representa uma carteira digital partilhada entre várias threads.
 *
 * Esta classe demonstra de forma prática:
 *  - Uma vulnerabilidade real de Race Condition (metodo inseguro)
 *  - A correção da mesma através do mecanismo synchronized do Java
 *
 * Usada para testes de concorrência e análise pelo SecurityMonitor.
 */
public class CarteiraDigital {

    private double saldo;
    private final SecurityMonitor monitor;

    /**
     * Construtor da carteira com saldo inicial.
     *
     * @param saldoInicial valor inicial da carteira (deve ser >= 0)
     */
    public CarteiraDigital(double saldoInicial) {
        if (saldoInicial < 0) {
            saldoInicial = 0;
        }
        this.saldo = saldoInicial;
        this.monitor = SecurityMonitor.obterInstancia();

        monitor.registarEvento("CARTEIRA",
                "Carteira digital criada com saldo inicial de " + saldoInicial + "€");
    }

    /**
     * Operação de deposito INSEGURO.
     *
     * Não possui qualquer proteção contra acesso concorrente.
     * Quando várias threads executam este metodo simultaneamente,
     * ocorre uma condição de corrida que pode corromper o saldo.
     */
    public void adicionarSaldoInseguro(double valor) {
        if (valor <= 0) {
            monitor.registarEvento(Thread.currentThread().getName(), "Tentativa de deposito inseguro com valor invalido: " + valor);
            return;
        }

        String nomeThread = Thread.currentThread().getName();
        monitor.registarEvento(nomeThread, " Deposito INSEGURO iniciado: " + valor + "€ (SEM sincronização - risco de race condition)");

        // Secção crítica = race condition
        double saldoAtual = saldo;

        try {
            Thread.sleep(50 + (long)(Math.random() * 100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        saldo = saldoAtual + valor;

        monitor.registarEvento(nomeThread, " Deposito inseguro concluído. Saldo atual: " + saldo + "€ (possível perda de dados)");
    }

    /**
     * Operação de deposito SEGURO.
     *
     * Utiliza o modificador 'synchronized' para garantir exclusão mútua.
     * Mesmo com várias threads a executar em paralelo, o saldo nunca será corrompido.
     */
    public synchronized void adicionarSaldoSeguro(double valor) {
        if (valor <= 0) {
            monitor.registarEvento(Thread.currentThread().getName(), "Tentativa de deposito seguro com valor invalido: " + valor);
            return;
        }

        String nomeThread = Thread.currentThread().getName();
        monitor.registarEvento(nomeThread, " Deposito SEGURO iniciado: " + valor + "€ (COM synchronized - exclusão mútua garantida)");

        double saldoAntes = saldo;

        try {
            Thread.sleep(50 + (long)(Math.random() * 100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        saldo = saldoAntes + valor;

        monitor.registarEvento(nomeThread, " Deposito seguro concluído. Saldo atual: " + saldo + "€ (integridade garantida)");
    }

    /**
     * Consulta o saldo atual da carteira.
     *
     * @return o saldo corrente
     */
    public synchronized double obterSaldo() {
        return saldo;
    }
}