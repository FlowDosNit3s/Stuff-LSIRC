package resources;

import monitor.SecurityMonitor;

public class ServidorBaseDeDados {

    private String nome;
    private SecurityMonitor monitor;

    public ServidorBaseDeDados(String nome) {
        this.nome = nome;
        this.monitor = SecurityMonitor.getInstance();
    }

    public String getNome() { return nome; }

    // --- VERSÃO INSEGURA (Causa Deadlock) ---
    public synchronized void processarTransacao(ServidorBaseDeDados outroServidor) {
        String threadName = Thread.currentThread().getName();
        monitor.logEvent(threadName, this.nome + ": Bloqueado para processamento.");

        try { Thread.sleep(100); } catch (InterruptedException e) {}

        monitor.logEvent(threadName, this.nome + ": A tentar aceder ao " + outroServidor.getNome() + "...");

        // Ponto crítico: tenta agarrar o segundo recurso enquanto segura o primeiro
        outroServidor.confirmarTransacao();
    }

    // --- VERSÃO SEGURA (Correção - Alínea D) ---
    // Estratégia: Ordenação de Recursos (Resource Ordering).
    public void processarTransacaoSegura(ServidorBaseDeDados outroServidor) {
        String threadName = Thread.currentThread().getName();

        // Define a ordem de bloqueio baseada no nome (ex: "A" vem antes de "B")
        ServidorBaseDeDados primeiro = this;
        ServidorBaseDeDados segundo = outroServidor;

        // Se o nome deste servidor for "maior" que o do outro, trocamos a ordem
        if (this.nome.compareTo(outroServidor.getNome()) > 0) {
            primeiro = outroServidor;
            segundo = this;
        }

        monitor.logEvent(threadName, "A tentar adquirir recursos na ordem: " + primeiro.getNome() + " -> " + segundo.getNome());

        synchronized (primeiro) {
            // Simulamos trabalho no primeiro recurso
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            synchronized (segundo) {
                monitor.logEvent(threadName, "Conseguiu ambos os recursos! A processar...");
                monitor.logEvent(threadName, "Transação SEGURA concluída.");
            }
        }
    }

    public synchronized void confirmarTransacao() {
        monitor.logEvent(Thread.currentThread().getName(), this.nome + ": Confirmação recebida.");
    }
}