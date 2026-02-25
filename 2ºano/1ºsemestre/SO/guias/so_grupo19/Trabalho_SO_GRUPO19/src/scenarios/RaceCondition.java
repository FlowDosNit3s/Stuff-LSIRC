package scenarios;

import monitor.SecurityMonitor;
import resources.CarteiraDigital;

/**
 *
 * Demonstra como uma thread pode ficar indefinidamente
 * sem acesso a um recurso quando outras threads
 * monopolizam o lock, e como uma política justa
 * (fair=true) resolve o problema.
 */

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {

        SecurityMonitor monitor = SecurityMonitor.obterInstancia();

        monitor.registarEvento("INFO", "══════════════════════════════════════════════════════════════");
        monitor.registarEvento("INFO", "CONCEITO: Race Condition (Condição de Corrida)");
        monitor.registarEvento("INFO", "DEFINIÇÃO: Múltiplas threads acedem a dados partilhados");
        monitor.registarEvento("INFO", "           sem sincronização, causando resultados imprevisíveis");
        monitor.registarEvento("INFO", "══════════════════════════════════════════════════════════════");
        monitor.registarEvento("TESTE-FASE-1", "DEMONSTRAÇÃO DO PROBLEMA (versão insegura)");
        monitor.registarEvento("INFO", "CENÁRIO: 2 threads depositam 100€ simultaneamente");
        monitor.registarEvento("INFO", "SALDO INICIAL: 0.0€  |  SALDO ESPERADO: 200.0€");
        monitor.registarEvento("INFO", "RESULTADO REAL: Perda de dados devido a race condition");

        CarteiraDigital carteiraMA = new CarteiraDigital(0);

        Thread hacker1 = new Thread(() -> carteiraMA.adicionarSaldoInseguro(100),"Hacker-1");
        Thread hacker2 = new Thread(() -> carteiraMA.adicionarSaldoInseguro(100),"Hacker-2");

        hacker1.start();
        hacker2.start();
        hacker1.join();
        hacker2.join();

        double saldo = carteiraMA.obterSaldo();
        double perdido = 200 - saldo;

        monitor.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");
        monitor.registarEvento("RESULTADO", "SALDO FINAL INSEGURO: " + saldo + "€");

        if(perdido > 0){
            monitor.registarEvento("ALERTA", " INCONSISTÊNCIA DETETADA!");
            monitor.registarEvento("ALERTA", " Valor perdido: " + perdido + "€");
            monitor.registarEvento("ALERTA", " CAUSA: Race condition - múltiplas threads leram o mesmo valor");
            monitor.registarEvento("ALERTA", " CONSEQUÊNCIA: Falha grave de integridade dos dados");
        } else {
            monitor.registarEvento("RESULTADO", " não houve perda desta vez (race condition não se manifestou)");
        }
        monitor.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");

        System.out.println("\n" + "=".repeat(60) + "\n");

        monitor.registarEvento("TESTE-FASE-2", "CORREÇÃO DO PROBLEMA (versão segura com synchronized)");
        monitor.registarEvento("INFO", "SOLUÇÃO: Modificador 'synchronized' garante exclusão mútua");
        monitor.registarEvento("INFO", "GARANTIA: Apenas 1 thread por vez pode executar o método");
        monitor.registarEvento("INFO", "RESULTADO ESPERADO: Integridade total dos dados (200.0€)");

        CarteiraDigital carteiraBoa = new CarteiraDigital(0);

        Thread admin1 = new Thread(() -> carteiraBoa.adicionarSaldoSeguro(100),"Admin-1");
        Thread admin2 = new Thread(() -> carteiraBoa.adicionarSaldoSeguro(100),"Admin-2");

        admin1.start();
        admin2.start();
        admin1.join();
        admin2.join();

        monitor.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");
        monitor.registarEvento("RESULTADO", "SALDO FINAL SEGURO: " + carteiraBoa.obterSaldo() + "€");

        if(carteiraBoa.obterSaldo() == 200){
            monitor.registarEvento("SUCESSO", " SUCESSO TOTAL - Integridade dos dados garantida!");
            monitor.registarEvento("SUCESSO", " Synchronized preveniu completamente a race condition");
        } else {
            monitor.registarEvento("ALERTA", "  Erro inesperado");
        }
        monitor.registarEvento("RESULTADO", "──────────────────────────────────────────────────────────────");

        monitor.gerarRelatorioEstatisticas();
        monitor.encerrar();
    }
}