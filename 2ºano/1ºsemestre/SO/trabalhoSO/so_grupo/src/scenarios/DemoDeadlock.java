package scenarios;

import monitor.SecurityMonitor;
import resources.ServidorBaseDeDados;

/**
 * Cenário 2: Deadlock (DoS) e respetiva correção.
 * Melhorado para ser visualmente explícito na consola.
 */
public class DemoDeadlock {

    public static void main(String[] args) {
        SecurityMonitor monitor = SecurityMonitor.getInstance();

        // =================================================================
        // FASE 1: A CORREÇÃO (O Comportamento Seguro)
        // =================================================================
        monitor.logEvent("TESTE-FASE-1", ">>> INICIANDO TESTE DE CORREÇÃO (PREVENÇÃO) <<<");
        monitor.logEvent("INFO", "Estratégia: Ordenação de Recursos.");
        monitor.logEvent("INFO", "As threads vão adquirir recursos sempre na mesma ordem (Alfabética).");

        ServidorBaseDeDados s1 = new ServidorBaseDeDados("Servidor-01");
        ServidorBaseDeDados s2 = new ServidorBaseDeDados("Servidor-02");

        Thread tSafe1 = new Thread(() -> s1.processarTransacaoSegura(s2), "Safe-Thread-1");
        Thread tSafe2 = new Thread(() -> s2.processarTransacaoSegura(s1), "Safe-Thread-2");

        tSafe1.start();
        tSafe2.start();

        try {
            tSafe1.join();
            tSafe2.join();
            monitor.logEvent("RESULTADO", ">>> SUCESSO: Fase 1 completa! Nenhuma thread ficou bloqueada.");
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("\n" + "=".repeat(60) + "\n");

        // =================================================================
        // FASE 2: O ATAQUE (Simulação DoS)
        // =================================================================
        monitor.logEvent("TESTE-FASE-2", ">>> INICIANDO SIMULAÇÃO DE ATAQUE DoS (DEADLOCK) <<<");
        monitor.logEvent("AVISO", "Cenário: Threads a pedir recursos de forma cruzada (A->B e B->A).");
        monitor.logEvent("AVISO", "O sistema vai CONGELAR em 2 segundos...");

        Thread tBad1 = new Thread(() -> s1.processarTransacao(s2), "DoS-Thread-1");
        Thread tBad2 = new Thread(() -> s2.processarTransacao(s1), "DoS-Thread-2");

        tBad1.start();
        tBad2.start();

        // Thread de Vigilância (Monitorização estilo eBPF)
        new Thread(() -> {
            try {
                // Deixa o Deadlock acontecer
                Thread.sleep(2000);

                monitor.logEvent("VIGILANCIA", "------------------------------------------");
                monitor.logEvent("VIGILANCIA", "A verificar sinais vitais do sistema...");

                // Esta função vai detetar o bloqueio e escrever o ALERTA CRÍTICO
                monitor.verificarDeadlocks();

                monitor.logEvent("VIGILANCIA", "------------------------------------------");
                monitor.logEvent("AVISO-FINAL", "O programa está tecnicamente morto (Deadlock).");
                monitor.logEvent("AVISO-FINAL", "Terá de terminar o processo manualmente (Stop/Kill).");

            } catch (InterruptedException e) {}
        }).start();
    }
}