package com.helio.ui;

import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.ui.SubmenuPessoa.submenuPessoa;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizada;

import java.util.Scanner;

public class Menu {

    public static void menu(Scanner scanner) {

        int opcao = -1;      
        do {
            limparTela();
            linhaPadronizada("MENU PRINCIPAL");
            System.out.println("1. Pessoa");
            System.out.println("2. ");
            System.out.println("4. ");
            System.out.println("5. \n");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");
             
            opcao = scanner.nextInt();
            scanner.nextLine();

            // Criado dubmenus para organizar
            switch (opcao) {
                case 1:
                    submenuPessoa(scanner);
                    break;                    
                case 2:
                    limparTela();
                    System.out.println("Listar Pessoa");
                    pausarExecucao(scanner);
                    break;
                case 3:
                    limparTela();
                    System.out.println("Editar Pessoa");
                    pausarExecucao(scanner);
                    break;
                case 4:
                    limparTela();
                    System.out.println("Remover Pessoa");
                    pausarExecucao(scanner);
                    break;
                case 0:
                    limparTela();
                    System.out.println("Saindo...");
                    pausarExecucao(scanner);
                    break;
                default:
                    limparTela();
                    System.out.println("Opção inválida. Tente novamente.");
                    pausarExecucao(scanner);
            }
        } while (opcao != 0);
    }
}
