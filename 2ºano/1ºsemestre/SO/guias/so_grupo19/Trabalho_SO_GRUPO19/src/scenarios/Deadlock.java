package scenarios;

import monitor.SecurityMonitor;
import resources.ServidorBaseDeDados;

/**
 * Classe de demonstração de deadlock.
 *
 * Mostra dois cenários:
 * 1) Execução segura, evitando deadlock através da ordenação de recursos.
 * 2) Execução insegura, provocando um deadlock intencional.
 *
 */
public class Deadlock {

    /**
     * Metodo principal.
     * Executa a simulação de prevenção e ocorrência de deadlock
     * utilizando múltiplas threads.
     *
     */
    public static void main(String[] args) {

        SecurityMonitor monitor = SecurityMonitor.obterInstancia();

        monitor.registarEvento("INFO", "══════════════════════════════════════════════════════════════");
        monitor.registarEvento("INFO", "CONCEITO: Deadlock (Bloqueio Mútuo)");
        monitor.registarEvento("INFO", "DEFINIÇÃO: Duas ou mais threads bloqueadas permanentemente,");
        monitor.registarEvento("INFO", "           cada uma esperando por um recurso detido pela outra");
        monitor.registarEvento("INFO", "══════════════════════════════════════════════════════════════");
        monitor.registarEvento("FASE-1", "INÍCIO DA SIMULAÇÃO SEGURA (prevenção de deadlock)");
        monitor.registarEvento("INFO", "TÉCNICA: Resource Ordering (ordenação alfabética de recursos)");
        monitor.registarEvento("INFO", "GARANTIA: Todas as threads adquirem locks na mesma ordem");
        monitor.registarEvento("INFO", "RESULTADO ESPERADO: Nenhum deadlock");

        ServidorBaseDeDados servidorA = new ServidorBaseDeDados("Servidor-01");
        ServidorBaseDeDados servidorB = new ServidorBaseDeDados("Servidor-02");

        Thread threadSegura1 = new Thread(() ->
                servidorA.processarTransacaoSegura(servidorB), "Thread-Segura-1");
        Thread threadSegura2 = new Thread(() ->
                servidorB.processarTransacaoSegura(servidorA), "Thread-Segura-2");

        threadSegura1.start();
        threadSegura2.start();

        try {
            threadSegura1.join();
            threadSegura2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        monitor.registarEvento("RESULTADO", "Fase 1 concluída com SUCESSO - nenhuma thread ficou bloqueada");
        monitor.registarEvento("RESULTADO", "Resource Ordering preveniu o deadlock");

        System.out.println("\n" + "=".repeat(70) + "\n");

        monitor.registarEvento("FASE-2", "INÍCIO DA SIMULAÇÃO INSEGURA (provocar deadlock)");
        monitor.registarEvento("INFO", "PROBLEMA: Aquisição cruzada de locks");
        monitor.registarEvento("INFO", "Thread-Ataque-1: bloqueia Servidor-01 → tenta Servidor-02");
        monitor.registarEvento("INFO", "Thread-Ataque-2: bloqueia Servidor-02 → tenta Servidor-01");
        monitor.registarEvento("INFO", "RESULTADO ESPERADO: Deadlock permanente (ciclo de espera)");

        Thread threadAtaque1 = new Thread(() ->
                servidorA.processarTransacaoInsegura(servidorB), "Thread-Ataque-1");
        Thread threadAtaque2 = new Thread(() ->
                servidorB.processarTransacaoInsegura(servidorA), "Thread-Ataque-2");

        threadAtaque1.start();
        threadAtaque2.start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);

                monitor.registarEvento("VERIFICAÇÃO", "---------------------------------------------");
                monitor.registarEvento("VERIFICAÇÃO", "Analise do estado das threads em curso...");

                monitor.detetarDeadlocks();

                monitor.registarEvento("VERIFICAÇÃO", "---------------------------------------------");
                monitor.registarEvento("CONCLUSÃO", "Deadlock detetado – o programa encontra-se bloqueado permanentemente.");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}