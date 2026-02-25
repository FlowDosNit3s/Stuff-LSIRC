package resources;

import monitor.SecurityMonitor;

/**
 * Simula dois servidores de base de dados que precisam de trocar informações
 * para validar transações bancárias distribuídas.
 *
 * Esta classe demonstra:
 *  • Como surge um deadlock clássico com aquisição circular de monitores
 *  • Como prevenir esse deadlock usando a técnica de Resource Ordering
 */
public class ServidorBaseDeDados {

    private final String nome;
    private final SecurityMonitor monitor;

    public ServidorBaseDeDados(String nome) {
        this.nome = nome;
        this.monitor = SecurityMonitor.obterInstancia();

        monitor.registarEvento("SISTEMA", "Servidor de base de dados '" + nome + "' inicializado e pronto.");
    }

    public String obterNome() {
        return nome;
    }

    /**
     * VERSÃO INSEGURA – Provoca deadlock quando executada concorrentemente.
     *
     * Cada thread bloqueia o seu próprio servidor (this) e depois tenta
     * bloquear o servidor remoto. Se duas threads fizerem o inverso,
     * cria-se um ciclo de espera → deadlock garantido.
     */
    public synchronized void processarTransacaoInsegura(ServidorBaseDeDados servidorRemoto) {
        String thread = Thread.currentThread().getName();

        monitor.registarEvento(thread, nome + " Transação INSEGURA iniciada (bloqueou " + nome + ")");

        // Simula trabalho local no primeiro servidor
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        monitor.registarEvento(thread, nome + " Tentando bloquear " + servidorRemoto.obterNome() + " (RISCO: aquisição cruzada de locks)");

        servidorRemoto.confirmarTransacao();

        monitor.registarEvento(thread, nome + " Transação concluída com sucesso");
    }

    /**
     * Metodo auxiliar usado pela versão insegura.
     */
    public synchronized void confirmarTransacao() {
        monitor.registarEvento(Thread.currentThread().getName(), nome + "Confirmacao de transacao recebida.");
    }

    /**
     * VERSÃO SEGURA – Previne deadlock através de Resource Ordering.
     *
     * A ideia é simples: todos os threads adquirem sempre os locks
     * na mesma ordem (por exemplo, alfabética). Assim nunca haverá ciclo.
     */
    public void processarTransacaoSegura(ServidorBaseDeDados servidorRemoto) {
        String thread = Thread.currentThread().getName();

        ServidorBaseDeDados primeiro = this;
        ServidorBaseDeDados segundo  = servidorRemoto;

        if (this.nome.compareTo(servidorRemoto.obterNome()) > 0) {
            primeiro = servidorRemoto;
            segundo  = this;
        }

        monitor.registarEvento(thread, "Resource Ordering aplicado: " + primeiro.obterNome() + " → " + segundo.obterNome() + " (ordem alfabética)");

        synchronized (primeiro) {
            monitor.registarEvento(thread, " " + primeiro.obterNome() + " bloqueado (1º lock adquirido)");

            try { Thread.sleep(80); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            synchronized (segundo) {
                monitor.registarEvento(thread, " " + segundo.obterNome() + " bloqueado (2º lock adquirido - deadlock IMPOSSÍVEL)");
                monitor.registarEvento(thread, " Transação SEGURA concluída entre " + this.nome + " e " + servidorRemoto.obterNome());
            }
        }
    }
}