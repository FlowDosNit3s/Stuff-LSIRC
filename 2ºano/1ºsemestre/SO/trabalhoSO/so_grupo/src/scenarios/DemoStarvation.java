package scenarios;

import monitor.SecurityMonitor;
import resources.SistemaFicheiros;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ArrayList;
import java.util.List;

/**
 * Cenário 3: Starvation.
 * Mostra explicitamente a thread de Auditoria a falhar se não houver justiça.
 */
public class DemoStarvation {

    public static void main(String[] args) {
        SecurityMonitor monitor = SecurityMonitor.getInstance();

        // FASE 1: STARVATION (Modo Injusto)
        monitor.logEvent("TESTE-FASE-1", ">>> SIMULAÇÃO DE STARVATION (Modo Injusto/Unfair) <<<");
        monitor.logEvent("INFO", "Cenário: Threads VIP (Alta Prioridade) vs Auditoria (Baixa Prioridade).");
        monitor.logEvent("INFO", "A Auditoria tem 3 segundos para conseguir entrar. A valer...");

        executarCenario(false, monitor);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // FASE 2: CORREÇÃO (Modo Justo)
        monitor.logEvent("TESTE-FASE-2", ">>> TESTE DA CORREÇÃO (Modo Justo/Fairness) <<<");
        monitor.logEvent("INFO", "A ativar fila FIFO. A prioridade da thread deixa de importar tanto.");

        executarCenario(true, monitor);

        monitor.imprimirEstatisticas();
        monitor.close();
    }

    private static void executarCenario(boolean modoJusto, SecurityMonitor monitor) {
        SistemaFicheiros sistema = new SistemaFicheiros(modoJusto);
        AtomicBoolean running = new AtomicBoolean(true);
        List<Thread> vipThreads = new ArrayList<>();

        // Criar tráfego intenso
        for (int i = 1; i <= 3; i++) {
            Thread tVip = new Thread(() -> {
                while (running.get()) {
                    sistema.lerFicheiroCritico();
                    try { Thread.sleep(1); } catch (InterruptedException e) {}
                }
            }, "VIP-" + i);
            tVip.setPriority(Thread.MAX_PRIORITY);
            tVip.start();
            vipThreads.add(tVip);
        }

        try { Thread.sleep(500); } catch (Exception e) {}

        // Tentar lançar a Auditoria
        Thread tAudit = new Thread(() -> {
            monitor.logEvent("AUDITORIA", "A pedir acesso ao sistema...");
            sistema.lerFicheiroCritico();
            monitor.logEvent("AUDITORIA", ">>> SUCESSO: Acesso conseguido! <<<");
        }, "AUDITORIA");

        tAudit.setPriority(Thread.MIN_PRIORITY);
        tAudit.start();

        try {
            tAudit.join(3000); // Espera max 3 segundos
        } catch (InterruptedException e) {}

        // Parar tudo
        running.set(false);
        for (Thread t : vipThreads) {
            try { t.join(); } catch (InterruptedException e) {}
        }

        if (tAudit.isAlive()) {
            monitor.logEvent("RESULTADO", "!!! FALHA CRÍTICA !!!");
            monitor.logEvent("RESULTADO", "A Auditoria NUNCA executou (Starvation Confirmado).");
            monitor.logEvent("RESULTADO", "Isto seria uma falha de segurança grave em produção.");
            tAudit.interrupt(); // Forçar paragem
        } else {
            monitor.logEvent("RESULTADO", ">>> SUCESSO: O sistema garantiu o acesso à Auditoria.");
        }
    }
}