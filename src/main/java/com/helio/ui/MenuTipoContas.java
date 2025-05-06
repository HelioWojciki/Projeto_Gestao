package com.helio.ui;

import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Validacao.entradaIntMenu;
import static com.helio.ui.SubmenuContaPoupanca.submenuContaPoupanca;

import java.util.Scanner;


public class MenuTipoContas {

    public static void menuTipoContas(Scanner scanner){
        int opcao = -1;      
        do {
            limparTela();
            linhaPadronizadaTitulo("TIPOS DE CONTA");
            System.out.println("    1. Gerenciar Conta Poupanca");

            System.out.println("\n0. Voltar ao Menu Principal");

            opcao = entradaIntMenu(scanner, "\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    submenuContaPoupanca(scanner);
                    break;
                case 2:
                    //repete para cc
                    break;
                
                case 0:
                    limparTela();
                    System.out.println("Voltando...");
                    pausarExecucao(scanner);
                break;
                default:
                    limparTela();
                    System.out.println("Opção inválida. Tente novamente.");
                    pausarExecucao(scanner);
                    break;
            }

        } while (opcao != 0);
    }
}
