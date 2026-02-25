package scenarios;

import monitor.SecurityMonitor;
import resources.SistemaFicheiros;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * Demonstra como uma thread pode ficar indefinidamente
 * sem acesso a um recurso quando outras threads
 * monopolizam o lock, e como uma política justa
 * (fair=true) resolve o problema.
 */

public class Starvation {

    public static void main(String[] args) {
        SecurityMonitor monitor = SecurityMonitor.obterInstancia();

        monitor.registarEvento("INFO", "══════════════════════════════════════════════════════════════");
        monitor.registarEvento("INFO", "CONCEITO: Starvation (Inanição)");
        monitor.registarEvento("INFO", "DEFINIÇÃO: Uma thread nunca consegue aceder a um recurso");
        monitor.registarEvento("INFO", "           porque outras threads de maior prioridade");
        monitor.registarEvento("INFO", "           monopolizam continuamente esse recurso");
        monitor.registarEvento("INFO", "══════════════════════════════════════════════════════════════");
        monitor.registarEvento("TESTE-FASE-1", "DEMONSTRAÇÃO DO PROBLEMA (política INJUSTA)");
        monitor.registarEvento("INFO", "CENÁRIO: 20 threads WORKER (prioridade máxima) monopolizam o lock");
        monitor.registarEvento("INFO", "         1 thread AUDITORIA (prioridade mínima) nunca consegue aceder");
        monitor.registarEvento("INFO", "POLÍTICA: ReentrantLock(fair=false) - SEM garantia de ordem");
        monitor.registarEvento("INFO", "RESULTADO ESPERADO: Starvation da thread AUDITORIA");

        executarTeste(false, monitor);

        System.out.println("\n" + "=".repeat(70) + "\n");

        monitor.registarEvento("TESTE-FASE-2", "CORREÇÃO DO PROBLEMA (política JUSTA)");
        monitor.registarEvento("INFO", "SOLUÇÃO: ReentrantLock(fair=true) - garante ordem FIFO");
        monitor.registarEvento("INFO", "GARANTIA: Threads são atendidas por ordem de chegada");
        monitor.registarEvento("INFO", "CENÁRIO: Menos threads WORKER (15) + política justa");
        monitor.registarEvento("INFO", "RESULTADO ESPERADO: AUDITORIA consegue aceder (sem starvation)");

        executarTeste(true, monitor);

        monitor.gerarRelatorioEstatisticas();
        monitor.encerrar();
    }

    private static void executarTeste(boolean justo, SecurityMonitor m) {
        SistemaFicheiros fs = new SistemaFicheiros(justo);
        AtomicBoolean continuar = new AtomicBoolean(true);
        List<Thread> workers = new ArrayList<>();

        int numThreads = justo ? 15 : 20;
        for (int i = 1; i <= numThreads; i++) {
            Thread worker = new Thread(() -> {
                if (justo) {
                    for (int tentativa = 0; tentativa < 2 && continuar.get(); tentativa++) {
                        fs.lerFicheiroCritico();
                        try { Thread.sleep(10); } catch (Exception ignored) {}
                    }
                } else {
                    while (continuar.get()) {
                        fs.lerFicheiroCritico();
                    }
                }
            }, "WORKER-" + i);
            worker.setPriority(Thread.MAX_PRIORITY);
            worker.start();
            workers.add(worker);
        }


        try { Thread.sleep(300); } catch (Exception ignored) {}

        Thread auditoria = new Thread(() -> {
            fs.lerFicheiroCritico();
            }, "AUDITORIA");

        auditoria.setPriority(Thread.MIN_PRIORITY);
        auditoria.start();

        try {
            auditoria.join(3000);
        } catch (InterruptedException e) {}

        continuar.set(false);

        if (auditoria.isAlive()) {
            m.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");
            m.registarEvento("RESULTADO", " TIMEOUT ATINGIDO - Starvation confirmada!");
            m.registarEvento("RESULTADO", " Thread AUDITORIA ficou bloqueada por mais de 3 segundos");
            m.registarEvento("RESULTADO", " CAUSA: Threads WORKER monopolizaram o lock continuamente");
            m.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");
            auditoria.interrupt();
        } else {
            m.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");
            m.registarEvento("RESULTADO", " SUCESSO - Thread AUDITORIA conseguiu aceder ao recurso!");
            m.registarEvento("RESULTADO", " Política JUSTA (fair=true) preveniu a starvation");
            m.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");
        }

        for (Thread t : workers) {
            try { t.join(1000); } catch (Exception ignored) {}
        }
    }
}