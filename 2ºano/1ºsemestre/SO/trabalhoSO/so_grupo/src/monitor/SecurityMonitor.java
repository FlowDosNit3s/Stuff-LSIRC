package monitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.management.ThreadInfo;

/**
 * Classe responsável por monitorizar eventos das threads e registar logs.
 * Simula o comportamento de observabilidade do eBPF.
 */
public class SecurityMonitor {

    private static SecurityMonitor instance;
    private BufferedWriter writer;
    private final String LOG_FILE = "registo_atividade.txt";

    // Mapa para guardar contagem de acessos por thread (Estatística)
    private ConcurrentHashMap<String, Integer> accessStats;

    /**
     * Construtor privado para garantir padrão Singleton.
     * Inicializa o ficheiro de log.
     */
    private SecurityMonitor() {
        accessStats = new ConcurrentHashMap<>();
        try {
            // 'true' para fazer append (não apagar o ficheiro a cada execução)
            writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
            logEvent("SISTEMA", "Monitor inicializado. A iniciar rastreio eBPF (simulado)...");
        } catch (IOException e) {
            System.err.println("Erro ao criar ficheiro de log: " + e.getMessage());
        }
    }

    /**
     * Obtém a instância única do monitor.
     */
    public static synchronized SecurityMonitor getInstance() {
        if (instance == null) {
            instance = new SecurityMonitor();
        }
        return instance;
    }

    /**
     * Regista um evento no ficheiro de log e na consola.
     * @param threadName Nome da thread que gerou o evento.
     * @param message Descrição do evento ou anomalia detetada.
     */
    public synchronized void logEvent(String threadName, String message) {
        String logEntry = String.format(" [Thread: %-15s] %s", threadName, message);

        // Atualiza estatísticas: Se a thread já existe soma 1, se não, inicia a 1
        accessStats.merge(threadName, 1, Integer::sum);

        System.out.println(logEntry); // Mostra na consola
        try {
            writer.write(logEntry);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica se existem threads em estado de Deadlock.
     * Utiliza a ThreadMXBean do Java para análise do sistema.
     */
    public void verificarDeadlocks() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreadIds = threadBean.findDeadlockedThreads();

        if (deadlockedThreadIds != null) {
            // Formatação visual agressiva para Alerta Crítico
            logEvent("MONITOR-SISTEMA", "##################################################");
            logEvent("MONITOR-SISTEMA", "!!! ALERTA DE SEGURANÇA: DEADLOCK DETETADO !!!");
            logEvent("MONITOR-SISTEMA", "Ataque DoS (Denial of Service) em curso.");
            logEvent("MONITOR-SISTEMA", "##################################################");

            ThreadInfo[] threadInfos = threadBean.getThreadInfo(deadlockedThreadIds);
            for (ThreadInfo info : threadInfos) {
                logEvent("MONITOR-SISTEMA", ">> THREAD BLOQUEADA: " + info.getThreadName() +
                        " (Estado: " + info.getThreadState() + ")");
                logEvent("MONITOR-SISTEMA", "   À espera do recurso bloqueado por: " + info.getLockOwnerName());
            }
        }
    }

    /**
     * Regista o tempo que uma thread esteve à espera para aceder a um recurso.
     * Se o tempo for superior ao limite, lança um alerta de Starvation.
     */
    public void registarTempoEspera(String threadName, long tempoEsperaMs) {
        // Vamos definir 1000ms (1 segundo) como o limite aceitável para este sistema
        long LIMITE_STARVATION = 1000;

        if (tempoEsperaMs > LIMITE_STARVATION) {
            logEvent("MONITOR-RISCO", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            logEvent("MONITOR-RISCO", "ALERTA: Starvation detetado na thread " + threadName);
            logEvent("MONITOR-RISCO", "Tempo de espera: " + tempoEsperaMs + "ms (CRÍTICO)");
            logEvent("MONITOR-RISCO", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    /**
     * Imprime as estatísticas finais de acesso.
     * Cumpre o requisito: "Manter estatísticas como número de acessos por thread".
     */
    public void imprimirEstatisticas() {
        logEvent("STATS", "========================================");
        logEvent("STATS", "RELATÓRIO FINAL DE ESTATÍSTICAS (eBPF)");
        logEvent("STATS", "========================================");

        accessStats.forEach((thread, count) -> {
            // Ignora threads de sistema/internas para limpar o log
            if(!thread.equals("SISTEMA") && !thread.equals("STATS") && !thread.equals("MONITOR-SISTEMA")) {
                logEvent("STATS", String.format("Thread [%-15s] -> %d eventos registados.", thread, count));
            }
        });
        logEvent("STATS", "========================================");
    }

    /**
     * Fecha o descritor do ficheiro ao terminar o programa.
     */
    public void close() {
        try {
            if (writer != null) {
                logEvent("SISTEMA", "Monitor encerrado.");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}