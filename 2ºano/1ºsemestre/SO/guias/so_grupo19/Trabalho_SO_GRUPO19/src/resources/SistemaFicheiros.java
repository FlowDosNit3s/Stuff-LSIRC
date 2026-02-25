package resources;

import monitor.SecurityMonitor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simula um sistema de ficheiros crítico onde múltiplas threads
 * tentam aceder a ficheiros protegidos por um mecanismo de bloqueio.
 *
 * Esta classe demonstra:
 *  • Uso de ReentrantLock com políticas justa e injusta
 *  • Registo de eventos e tempos de espera no SecurityMonitor
 */
public class SistemaFicheiros {

    private final SecurityMonitor monitor;
    private final ReentrantLock chave;
    private final String politica;
    private final boolean isFair;

    /**
     * Construtor do sistema de ficheiros.
     *
     * @param fair se true, usa política justa; se false, política injusta
     */
    public SistemaFicheiros(boolean fair) {
        this.monitor = SecurityMonitor.obterInstancia();
        this.politica = fair ? "JUSTO" : "INJUSTO";
        this.chave = new ReentrantLock(fair);
        this.isFair = fair;

        monitor.registarEvento("SISTEMA", "Sistema de ficheiros iniciado: " + politica);
    }

    /**
     * Metodo que simula a leitura de um ficheiro crítico.
     *
     * Usa um ReentrantLock com política justa ou injusta.
     * Regista eventos e tempos de espera no SecurityMonitor.
     */
    public void lerFicheiroCritico() {
        String nome = Thread.currentThread().getName();
        long chegada = System.currentTimeMillis();

        monitor.registarEvento(nome, "A tentar obter lock do ficheiro [Política: " + politica + "]");

        try {
            chave.lockInterruptibly();

            try {
                long entrada = System.currentTimeMillis();
                long espera = entrada - chegada;

                if (!isFair) {
                    monitor.registarEspera(nome, espera);
                } else if (espera > 3000) {
                    monitor.registarEspera(nome, espera);
                }

                monitor.registarEvento(nome, " Lock adquirido! A aceder ao ficheiro(Tempo de espera: " + espera + "ms)");

                Thread.sleep(100);

            } finally {
                chave.unlock();
                monitor.registarEvento(nome, " Lock libertado (ficheiro fechado)");
            }

        } catch (InterruptedException e) {
            long tempoPerdido = System.currentTimeMillis() - chegada;

            monitor.registarEvento(nome, " INTERROMPIDO após " + tempoPerdido + "ms de espera (nunca conseguiu o lock)");
            monitor.registarEspera(nome, tempoPerdido);

            Thread.currentThread().interrupt();
        }
    }
}