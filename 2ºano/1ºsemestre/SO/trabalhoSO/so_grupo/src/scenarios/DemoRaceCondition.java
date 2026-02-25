package scenarios;

import monitor.SecurityMonitor;
import resources.CarteiraDigital;

/**
 * Cenário 1: Race Condition (Corrupção de Dados).
 * Mostra explicitamente a diferença de valores (Dinheiro roubado).
 */
public class DemoRaceCondition {

    public static void main(String[] args) throws InterruptedException {
        SecurityMonitor monitor = SecurityMonitor.getInstance();

        // =================================================================
        // FASE 1: O ERRO (Vulnerabilidade)
        // =================================================================
        monitor.logEvent("TESTE-FASE-1", ">>> INICIANDO TESTE DE VULNERABILIDADE (RACE CONDITION) <<<");
        monitor.logEvent("INFO", "Cenário: 2 Hackers tentam depositar 100€ ao mesmo tempo sem proteção.");
        monitor.logEvent("INFO", "Valor Esperado: 200.0 | Valor Inicial: 0.0");

        CarteiraDigital carteiraVulneravel = new CarteiraDigital(0);

        Thread t1 = new Thread(() -> carteiraVulneravel.adicionarSaldoInseguro(100), "Hacker-1");
        Thread t2 = new Thread(() -> carteiraVulneravel.adicionarSaldoInseguro(100), "Hacker-2");

        t1.start(); t2.start();
        t1.join(); t2.join();

        double saldoObtido = carteiraVulneravel.getSaldo();
        double perda = 200 - saldoObtido;

        monitor.logEvent("RESULTADO", "Saldo Final na Carteira: " + saldoObtido);

        if (perda > 0) {
            monitor.logEvent("ALERTA", "!!! CORRUPÇÃO DE DADOS DETETADA !!!");
            monitor.logEvent("ALERTA", "Valor Desaparecido: " + perda + "€");
            monitor.logEvent("ALERTA", "Isto representa uma falha grave de integridade.");
        }

        System.out.println("\n" + "=".repeat(60) + "\n");

        // =================================================================
        // FASE 2: A CORREÇÃO
        // =================================================================
        monitor.logEvent("TESTE-FASE-2", ">>> INICIANDO TESTE DA CORREÇÃO (SYNCHRONIZED) <<<");
        monitor.logEvent("INFO", "As mesmas operações, mas agora protegidas por Monitor (Exclusão Mútua).");

        CarteiraDigital carteiraSegura = new CarteiraDigital(0);

        Thread t3 = new Thread(() -> carteiraSegura.adicionarSaldoSeguro(100), "Admin-1");
        Thread t4 = new Thread(() -> carteiraSegura.adicionarSaldoSeguro(100), "Admin-2");

        t3.start(); t4.start();
        t3.join(); t4.join();

        monitor.logEvent("RESULTADO", "Saldo Final Seguro: " + carteiraSegura.getSaldo());

        if (carteiraSegura.getSaldo() == 200) {
            monitor.logEvent("SUCESSO", "Integridade dos dados mantida. O sistema é seguro.");
        }

        monitor.imprimirEstatisticas();
        monitor.close();
    }
}