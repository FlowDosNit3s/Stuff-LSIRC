package resources;

import monitor.SecurityMonitor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Recurso partilhado que simula um Sistema de Ficheiros Seguro.
 * Permite alternar entre modo Injusto (Starvation) e Justo (Fairness).
 */
public class SistemaFicheiros {

    private SecurityMonitor monitor;
    private ReentrantLock lock;
    private String modo;

    /**
     * @param usarJustica Se 'true', ativa a política FIFO (Fairness).
     * Se 'false', permite que threads rápidas furem a fila (Starvation).
     */
    public SistemaFicheiros(boolean usarJustica) {
        this.monitor = SecurityMonitor.getInstance();
        this.modo = usarJustica ? "JUSTO (Fair)" : "INJUSTO (Unfair)";

        // A flag 'true' no construtor do ReentrantLock resolve o Starvation
        this.lock = new ReentrantLock(usarJustica);
    }

    public void lerFicheiroCritico() {
        String threadName = Thread.currentThread().getName();

        // 1. Registar quando a thread entrou na fila de espera
        long horaChegada = System.currentTimeMillis();

        // Tenta adquirir o acesso (bloqueia se ocupado)
        lock.lock();

        try {
            // 2. Calcular tempo de espera
            long horaEntrada = System.currentTimeMillis();
            long tempoEspera = horaEntrada - horaChegada;

            // Requisito c): Registar ocorrências no log
            monitor.registarTempoEspera(threadName, tempoEspera);

            monitor.logEvent(threadName, ">>> Acedeu ao ficheiro [" + modo + "].");

            // Simular leitura pesada (ocupa o recurso por um tempo)
            try { Thread.sleep(50); } catch (InterruptedException e) {}

        } finally {
            monitor.logEvent(threadName, "<<< Saiu do ficheiro.");
            lock.unlock(); // Libertar recurso
        }
    }
}