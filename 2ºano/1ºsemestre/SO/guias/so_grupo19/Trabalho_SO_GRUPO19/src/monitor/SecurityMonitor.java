package monitor;

import java.io.*;
import java.lang.management.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Monitor de segurança completo.
 * Capaz de registar eventos, detetar Deadlocks e alertar sobre Starvation.
 */
public class SecurityMonitor {

    private static SecurityMonitor instancia;
    private BufferedWriter escritor;
    private final String FICHEIRO_LOG = "logs.txt";

    // Mapa para estatísticas
    private final ConcurrentHashMap<String, Integer> estatisticasThread = new ConcurrentHashMap<>();

    private SecurityMonitor() {
        try {
            escritor = new BufferedWriter(new FileWriter(FICHEIRO_LOG, true));
            registarEvento("SISTEMA", "Monitor de segurança iniciado.");
        } catch (IOException e) {
            System.err.println("Erro ao inicializar log: " + e.getMessage());
        }
    }

    public static synchronized SecurityMonitor obterInstancia() {
        if (instancia == null) {
            instancia = new SecurityMonitor();
        }
        return instancia;
    }

    public synchronized void registarEvento(String nomeThread, String mensagem) {
        String linha = String.format("[%-20s] %s", nomeThread, mensagem);

        estatisticasThread.merge(nomeThread, 1, Integer::sum);

        System.out.println(linha);

        try {
            if (escritor != null) {
                escritor.write(linha);
                escritor.newLine();
                escritor.flush();
            }
        } catch (IOException e) {
            System.err.println("Erro escrita log: " + e.getMessage());
        }
    }

    /**
     * Verifica se existem threads em Deadlock usando a JMX (ThreadMXBean).
     */
    public void detetarDeadlocks() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] idsDeadlock = bean.findDeadlockedThreads();

        if (idsDeadlock != null && idsDeadlock.length > 0) {
            registarEvento("ALERTA-SEGURANÇA", "========================================");
            registarEvento("ALERTA-SEGURANÇA", "DEADLOCK DETETADO!");
            registarEvento("ALERTA-SEGURANÇA", "========================================");

            ThreadInfo[] infos = bean.getThreadInfo(idsDeadlock, true, true);
            for (ThreadInfo info : infos) {
                if (info != null) {
                    String msg = String.format("Thread '%s' bloqueada por '%s'",
                            info.getThreadName(),
                            info.getLockOwnerName());
                    registarEvento("ALERTA-SEGURANÇA", msg);
                }
            }
        }
    }

    /**
     * Regista tempo de espera e lança alerta se for Starvation.
     */
    public void registarEspera(String nomeThread, long tempoEsperaMs) {
        final long LIMITE_STARVATION = 1000; // 1 segundo

        if (tempoEsperaMs > LIMITE_STARVATION) {
            registarEvento("ALERTA-RISCO", "**************************************************");
            registarEvento("ALERTA-RISCO", "Starvation detetada na thread: " + nomeThread);
            registarEvento("ALERTA-RISCO", "Tempo de espera excessivo: " + tempoEsperaMs + " ms");
            registarEvento("ALERTA-RISCO", "**************************************************");
        }
    }

    public void gerarRelatorioEstatisticas() {
        registarEvento("RELATÓRIO", "==========================================");
        registarEvento("RELATÓRIO", "Relatorio final de atividade");
        registarEvento("RELATÓRIO", "==========================================");

        estatisticasThread.forEach((k, v) -> {
            if (!k.startsWith("SISTEMA") && !k.startsWith("RELATORIO") && !k.startsWith("ALERTA")) {
                registarEvento("RELATORIO", String.format("Thread: %-20s -> %4d eventos", k, v));
            }
        });

        registarEvento("RELATORIO", "==========================================");
    }

    public void encerrar() {
        try {
            if (escritor != null) {
                registarEvento("SISTEMA", "Monitor encerrado.");
                escritor.close();
            }
        } catch (IOException e) {}
    }
}