package resources;

import monitor.SecurityMonitor;

/**
 * Recurso partilhado que simula uma carteira digital.
 * Contém vulnerabilidade de Race Condition (Condição de Corrida).
 */
public class CarteiraDigital {

    private double saldo;
    private SecurityMonitor monitor;

    public CarteiraDigital(double saldoInicial) {
        this.saldo = saldoInicial;
        this.monitor = SecurityMonitor.getInstance();
    }

    /**
     * Método inseguro para adicionar fundos.
     * Simula uma falha de segurança onde o saldo é corrompido.
     */
    public void adicionarSaldoInseguro(double valor) {
        String threadName = Thread.currentThread().getName();
        monitor.logEvent(threadName, "A tentar adicionar saldo (Inseguro): " + valor);

        // --- INICIO DA SECÇÃO CRÍTICA (NÃO PROTEGIDA) ---
        double temp = saldo;

        // Simular processamento (força a troca de contexto pelo SO para garantir o erro)
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        temp = temp + valor;
        saldo = temp;
        // --- FIM DA SECÇÃO CRÍTICA ---

        monitor.logEvent(threadName, "Saldo atualizado. Novo saldo: " + saldo);
    }

    /**
     * Método seguro utilizando 'synchronized' (Monitor do Java).
     * Corrige a vulnerabilidade (Exclusão Mútua).
     */
    public synchronized void adicionarSaldoSeguro(double valor) {
        String threadName = Thread.currentThread().getName();
        // monitor.logEvent(threadName, "A adicionar saldo de forma segura...");

        // --- INICIO DA SECÇÃO CRÍTICA (PROTEGIDA) ---
        double temp = saldo;
        try {
            Thread.sleep(100); // Mesmo com sleep, aqui é seguro
        } catch (InterruptedException e) {}

        saldo = temp + valor;
        // --- FIM DA SECÇÃO CRÍTICA ---

        monitor.logEvent(threadName, "Saldo atualizado (Seguro). Novo saldo: " + saldo);
    }

    public double getSaldo() {
        return saldo;
    }
}